import { UserDetails } from './models/user-models/user-details.model';
import { Playlist } from './models/playlist.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root'
})

export class PlaylistService {

  private readonly baseUrl = 'http://localhost:8000/playlists';  // url for playlist API to review
 
  userId:number = -99;
  
  constructor(private http:HttpClient, private userService:UsersService) { }


  getUserId(){
    let userDetails:UserDetails;
    this.userService.getUserDetails().subscribe(resp=>{
      userDetails = resp;
      this.userId = userDetails.id;
    }); 
  }

  getAllPlaylists(): Observable<any> {
    return this.http.get(`${this.baseUrl}`,{headers:this.userService.composeHeader()});
  }

  getPlaylistByUserId(userId:number): Observable<any> {
    return this.http.get(`${this.baseUrl}/user/${this.userId}`, {headers:this.userService.composeHeader()});
  }

  getPlaylistById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`,{headers:this.userService.composeHeader()});
  }

  createPlaylist(playlist: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, playlist,{headers:this.userService.composeHeader()});
  }

  changeTitle(title: string, id:number): Observable<Object> {
    return this.http.patch(`${this.baseUrl}/${id}`, title, {headers:this.userService.composeHeader()});
  }

  deletePlaylist(playlistId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${playlistId}`, {headers:this.userService.composeHeader()});
  }
}