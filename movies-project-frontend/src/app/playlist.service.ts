import { Movie } from './models/movie.model';
import { Playlist } from './models/playlist.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class PlaylistService {

  private readonly baseUrl = 'TODO';  // url for playlist API to add

  constructor(private http:HttpClient) { }

  createPlaylist(playlist: Object): Observable<Object> {  //parameters and URL to review
    return this.http.post(`${this.baseUrl}`, playlist);
  }

  getPlaylist(id: number): Observable<Playlist> {   //parameters and URL to review
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  addToPlaylist(playlistId: number, movie: Movie): Observable<Playlist> {   //parameters and URL to review
    return this.http.post(`${this.baseUrl}/${playlistId}`, movie);
  }

  deleteFromPlaylist(playlistId: number, movieId: number): Observable<any> {    //parameters and URL to review
    return this.http.delete(`${this.baseUrl}/${playlistId}/${movieId}`, {responseType: 'text'});
  }
}
