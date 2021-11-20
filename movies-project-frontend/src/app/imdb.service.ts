import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { MovieDetails } from './models/movie-details.model';

@Injectable({
  providedIn: 'root'
})

export class ImdbService {

  private readonly baseUrl = 'https://imdb-api.com';
  private readonly apiKey = 'k_dnuh97m7';

  constructor(private http:HttpClient) { }

  getMovieByTitle(title: string) {
    return this.http.get<any>(this.baseUrl + '/API/SearchTitle/' + this.apiKey + '/' + title, {observe: 'response'});
  }

  getMovieDetails(apiKey: string, movieId: string) :Observable<MovieDetails> {
    return this.http.get<MovieDetails>(this.baseUrl + '/en/API/Title/' + apiKey + '/' + movieId);
  }
}

