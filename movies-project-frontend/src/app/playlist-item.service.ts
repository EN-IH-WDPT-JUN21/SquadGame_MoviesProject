import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlaylistItemService {

  private readonly baseUrl = 'http://localhost:8000/playlist-item';  // url for playlist API to review
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Access-Control-Allow-Origin': '*',
      Authorization: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiZXhwIjoxNjM3NDMzMzE3fQ._UyJc4ouUWEyhBrwsoP0arwq055s3mKdk093lKHAFvvlscUVglrl6IX1CE7pa9y4XLQR0UmFPWaipXgk-DUVzw'
    })
  };

  constructor(private http:HttpClient) { }

  getPlaylistItemById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, {'headers': this.httpOptions.headers});
  }

  getPlaylistItemsByPlaylistTitleAndUserId(playlistTitle:string, userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/playlist-title/${playlistTitle}/${userId}`, {'headers': this.httpOptions.headers});
  }

  createPlaylistItem(playlistItem: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, playlistItem, {'headers': this.httpOptions.headers});
  }

  deletePlaylistItem(playlistItemId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${playlistItemId}`, {'headers': this.httpOptions.headers});
  }

  deleteAllPlaylistItem(playlistTitle:string, userId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${userId}/${playlistTitle}`, {'headers': this.httpOptions.headers});
  }
}
