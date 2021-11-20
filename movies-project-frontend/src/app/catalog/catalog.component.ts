import { MovieResponse } from './../models/movie.model';
import { ImdbService } from './../imdb.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  faSearch = faSearch;
  movies:MovieResponse[];
  search:string;
  
  constructor( private imdbService:ImdbService) {
    this.search="";
    this.movies = [];
  }

  ngOnInit(): void {
  }

  onSubmit():void{
    this.imdbService.getMovieByTitle(this.search).subscribe(data =>{
      this.movies.push(data);
     // const id:string = movieResponse.results[0].results.id;
      //const title:string = movieResponse.results[0].results.title;
      //const image:string = movieResponse.results[0].results.image;
      //const description:string = movieResponse.results[0].results.description;
      //this.movies.push(data);
        });

    //console.log(this.movies);
  }
  
  activateAddButton(index:number):void{

  }

}
