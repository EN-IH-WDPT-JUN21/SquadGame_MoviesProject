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
  
  constructor(private http:HttpClient, private userService:UsersService) { }

  getAllPlaylists(): Observable<any> {
    return this.http.get(`${this.baseUrl}`,{headers:this.userService.composeHeader()});
  }

  getPlaylistByUserId(): Observable<any> {
    return this.http.get(`${this.baseUrl}/user`, {headers:this.userService.composeHeader()});
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