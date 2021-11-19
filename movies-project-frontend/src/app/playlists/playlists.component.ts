import { PlaylistRequestId } from './../models/playlist-request.model';
import { PlaylistItem } from './../models/playlist-item.model';
import { PlaylistService } from './../playlist.service';
import { Component, OnInit } from '@angular/core';
import { Playlist } from '../models/playlist.model';
import { PlaylistRequest } from '../models/playlist-request.model';
import { PlaylistItemService } from '../playlist-item.service';

@Component({
  selector: 'app-playlists',
  templateUrl: './playlists.component.html',
  styleUrls: ['./playlists.component.css']
})
export class PlaylistsComponent implements OnInit {

  playlists:Playlist[];
  playlistItems:PlaylistItem[];
  playlist!:PlaylistRequest;
  title:string;
  images:string[] = [];
  userId:number = 3;
  isClicked:boolean;
  isEdited:boolean;
  index!:number;

  constructor(private playlistService:PlaylistService, private playlistItemService:PlaylistItemService) {
    this.playlists=[];
    this.playlistItems = [];
    this.title = "";
    this.isClicked = false;
    this.isEdited = false;
    this.images.push("../../assets/playlist-icon1.jpg");
    this.images.push("../../assets/playlist-icon2.jpg");
    this.images.push("../../assets/playlist-icon3.jpg");
    this.images.push("../../assets/playlist-icon4.jpg");
    this.images.push("../../assets/playlist-icon5.jpg");
    this.images.push("../../assets/playlist-icon6.jpg");
    this.images.push("../../assets/playlist-icon7.jpg");
    this.images.push("../../assets/playlist-icon8.jpg");
    this.images.push("../../assets/playlist-icon9.jpg");
   }

  ngOnInit(): void {
    this.refreshPlaylists();
  }

  randomImage():string{
    let index = 0;
    index = Math.floor(Math.random() * this.images.length);
    return this.images[index];
  }

  refreshPlaylists(){
    this.playlistService.getAllPlaylists().subscribe(data =>{
      this.playlists = data;
    });
  }

  createPlaylist():void{
   this.playlist =new PlaylistRequest(this.userId, this.title, false);
   console.log(this.playlist);
   this.playlistService.createPlaylist(this.playlist)
    .subscribe(data => {
     console.log(data)
     this.refreshPlaylists();
    })
  }

  showDetails(index:number){
    this.isClicked = true;
    this.playlistItemService.getPlaylistItemsByPlaylistTitleAndUserId(this.playlists[index].title, this.userId).subscribe(data =>{
      this.playlistItems = data;
    });

  }

  editPlaylistTitle(){
    this.playlistService.changeTitle(this.title, this.playlists[this.index].playlistId).subscribe(data =>{
      this.refreshPlaylists();
    });
    this.isEdited=false;
    this.title = "";
  }

  deletePlaylist(index:number):void{
    this.playlistService.deletePlaylist(this.playlists[index].playlistId)
    .subscribe(data => {
     console.log(data)
     this.refreshPlaylists();
    })
  }

  activeEditInput(index:number){
    this.index = index;
    this.isEdited = !this.isEdited;
  }
}

