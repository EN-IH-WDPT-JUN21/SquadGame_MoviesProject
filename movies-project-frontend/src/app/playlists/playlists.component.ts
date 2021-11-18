import { PlaylistService } from './../playlist.service';
import { Component, OnInit } from '@angular/core';
import { Playlist } from '../models/playlist.model';
import { PlaylistRequest } from '../models/playlist-request.model';

@Component({
  selector: 'app-playlists',
  templateUrl: './playlists.component.html',
  styleUrls: ['./playlists.component.css']
})
export class PlaylistsComponent implements OnInit {

  playlists:Playlist[];
  playlist!:PlaylistRequest;
  isEmpty: boolean;
  title:string;

  constructor(private playlistService:PlaylistService) {
    this.playlists=[];
    this.isEmpty = true;
    this.title = "";
   }

  ngOnInit(): void {
    this.refreshPlaylists();
  }

  refreshPlaylists(){
    this.playlistService.getAllPlaylists().subscribe(data =>{
      this.playlists = data;
    });
  }

  createPlaylist():void{
   this.playlist =new PlaylistRequest(23,3, this.title, false);
   console.log(this.playlist);
   this.playlistService.createPlaylist(this.playlist)
    .subscribe(data => {
     console.log(data)
     this.refreshPlaylists();
    })      
  }

  getUserPlaylist():void{

  }

  deletePlaylist(index:number):void{

  }
}

