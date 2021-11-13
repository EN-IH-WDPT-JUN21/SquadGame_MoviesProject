import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Movie } from './models/movie.model';
import { MovieDetails } from './models/movie-details.model';

@Injectable({
  providedIn: 'root'
})

export class ImdbService {

  private readonly baseUrl = 'https://imdb-api.com';

  constructor(private http:HttpClient) { }

  getMovieByTitle(apiKey: string, title: string): Observable<Movie> {
    return this.http.get<Movie>(this.baseUrl + '/API/SearchMovie/' + apiKey + '/' + title);
  }

  getMovieDetails(apiKey: string, movieId: string) :Observable<MovieDetails> {
    return this.http.get<MovieDetails>(this.baseUrl + '/en/API/Title/' + apiKey + '/' + movieId);
  }
}