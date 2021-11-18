import { Movie } from './models/movie.model';
import { Playlist } from './models/playlist.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class PlaylistService {

  private readonly baseUrl = 'http://localhost:8000/playlists';  // url for playlist API to review
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiZXhwIjoxNjM3MzQyNjcwfQ.B6ob4IMs2pwz6PDmvBUBjC6Jcix3dVlTyAKJ6T4sROxAXjSyQQabMgPcnrHWQYPHewbYVaydhBLfa2afqMlIQw'
    })
  };

  constructor(private http:HttpClient) { }

  getAllPlaylists(): Observable<any> {
    return this.http.get(`${this.baseUrl}`,{'headers': this.httpOptions.headers});
  }

  getPlaylistByUserId(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/user/${userId}`, {'headers': this.httpOptions.headers});
  }

  getPlaylistById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, {'headers': this.httpOptions.headers});
  }

  createPlaylist(playlist: Object): Observable<Object> {
    const body=JSON.stringify(playlist);
    console.log("Check payload" , playlist);
    console.log("Check body" , body);
    return this.http.post(`${this.baseUrl}`, playlist, {'headers': this.httpOptions.headers});
  }

  changeTitle(playlist: Playlist, id:number): Observable<Object> {
    return this.http.patch(`${this.baseUrl}/${id}`, playlist);
  }

  deletePlaylist(playlistId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${playlistId}`, {responseType: 'text'});
  }
}
