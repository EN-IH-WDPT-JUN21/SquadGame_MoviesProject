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
      'Access-Control-Allow-Origin': '*',
      Authorization: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiZXhwIjoxNjM3NDMzMzE3fQ._UyJc4ouUWEyhBrwsoP0arwq055s3mKdk093lKHAFvvlscUVglrl6IX1CE7pa9y4XLQR0UmFPWaipXgk-DUVzw'
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
    return this.http.post(`${this.baseUrl}`, playlist, {'headers': this.httpOptions.headers});
  }

  changeTitle(title: string, id:number): Observable<Object> {
    return this.http.patch(`${this.baseUrl}/${id}`, title, {'headers': this.httpOptions.headers});
  }

  deletePlaylist(playlistId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${playlistId}`, {'headers': this.httpOptions.headers});
  }
}
