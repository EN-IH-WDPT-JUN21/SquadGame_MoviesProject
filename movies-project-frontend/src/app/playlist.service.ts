import { Movie } from './models/movie.model';
import { Playlist } from './models/playlist.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class PlaylistService {

  private readonly baseUrl = 'localhost:8888/playlists';  // url for playlist API to review

  constructor(private http:HttpClient) { }

  getAllPlaylists(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getPlaylistByUserId(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/user/${userId}`);
  }

  getPlaylistById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createPlaylist(playlist: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, playlist);
  }

  changeTitle(playlist: Playlist, id:number): Observable<Object> {
    return this.http.patch(`${this.baseUrl}/${id}`, playlist);
  }

  deletePlaylist(playlistId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${playlistId}`, {responseType: 'text'});
  }
}
