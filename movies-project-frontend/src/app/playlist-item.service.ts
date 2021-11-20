import { UsersService } from './users.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlaylistItemService {

  private readonly baseUrl = 'http://localhost:8000/playlist-item';  // url for playlist API to review

  constructor(private http:HttpClient, private userService: UsersService) { }

  getPlaylistItemById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, {headers:this.userService.composeHeader()});
  }

  getPlaylistItemsByPlaylistTitleAndUserId(playlistTitle:string, userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/playlist-title/${playlistTitle}/${userId}`, {headers:this.userService.composeHeader()});
  }

  createPlaylistItem(playlistItem: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, playlistItem, {headers:this.userService.composeHeader()});
  }

  deletePlaylistItem(playlistItemId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${playlistItemId}`, {headers:this.userService.composeHeader()});
  }

  deleteAllPlaylistItem(playlistTitle:string, userId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${userId}/${playlistTitle}`, {headers:this.userService.composeHeader()});
  }
}
