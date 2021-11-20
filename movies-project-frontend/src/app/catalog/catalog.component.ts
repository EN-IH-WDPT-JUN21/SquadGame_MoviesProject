import { PlaylistItemService } from './../playlist-item.service';
import { PlaylistItemRequest } from './../models/playlist-item-request.model';
import { Playlist } from './../models/playlist.model';
import { PlaylistService } from './../playlist.service';
import { MovieInfo } from './../models/movie.model';
import { ImdbService } from './../imdb.service';
import { Component, OnInit } from '@angular/core';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  faSearch = faSearch;
  movies:MovieInfo[];
  search:string;
  addButtonIsActive:boolean;
  isTouched:boolean;
  isClicked:boolean;
  index:number;
  title:string;
  playlists:Playlist[];
  selectedOption:number;
  playlist!:Playlist;
  playlistItem!:PlaylistItemRequest;

  constructor( private imdbService:ImdbService, private playlistService:PlaylistService, private palylistItemService: PlaylistItemService) {
    this.search="";
    this.movies = [];
    this.addButtonIsActive = false;
    this.isTouched = false;
    this.isClicked = false;
    this.index = 0;
    this.title = "";
    this.playlists=[];
    this.selectedOption = 0;
  }

  ngOnInit(): void {
  }

  onSubmit():void{
    this.imdbService.getMovieByTitle(this.search).subscribe(data =>{
      console.log("data from the imdb servcie response:",data);
      this.movies = data.body.results;
    });

    this.isTouched = true;
  }

  activateAddButton(index:number):void{
    this.isClicked = true;
    this.index = index;
    this.playlistService.getPlaylistByUserId().subscribe(data =>{
      this.playlists = data;
    });
  }

  addToThePlaylist(){
    console.log(this.selectedOption);
    this.playlistService.getPlaylistById(this.selectedOption).subscribe(data =>{
      this.playlist = data;
      this.playlistItem = new PlaylistItemRequest(this.movies[this.index].id, this.movies[this.index].title, this.movies[this.index].description, this.movies[this.index].image, this.playlist);
      this.palylistItemService.createPlaylistItem(this.playlistItem).subscribe(data =>{
      })
    });

    this.isClicked = false;
    this.index=0;
  }
}
