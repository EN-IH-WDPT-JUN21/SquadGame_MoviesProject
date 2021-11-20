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
  itemIsClicked:boolean;
  index!:number;
  itemIndex!:number;

  constructor(private playlistService:PlaylistService, private playlistItemService:PlaylistItemService) {
    this.playlists=[];
    this.playlistItems = [];
    this.title = "";
    this.isClicked = false;
    this.isEdited = false;
    this.itemIsClicked = false;
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

  addImage(index:number):string{
    if(index > this.images.length){
      index = Math.floor(Math.random() * this.images.length);
    } 
    return this.images[index]; 
  }

  refreshPlaylists():void{
    this.playlistService.getPlaylistByUserId(this.userId).subscribe(data =>{
      this.playlists = data;
    });
  }

  refreshPlaylistsItem(index:number):void{
    this.playlistItemService.getPlaylistItemsByPlaylistTitleAndUserId(this.playlists[index].title, this.userId).subscribe(data =>{
    this.playlistItems = data;
    });
  }

  createPlaylist():void{
   this.playlist = new PlaylistRequest(this.userId, this.title, false)
   console.log(this.playlist);
   this.playlistService.createPlaylist(this.playlist)
    .subscribe(data => {
     console.log(data)
     this.refreshPlaylists();
    });
  }

  showDetails(index:number):void{
    this.isClicked = true;
    this.index = index;
    this.refreshPlaylistsItem(index);
  }

  activeEditInput(index:number):void{
    this.index = index;
    this.isEdited = !this.isEdited;
  }

  editPlaylistTitle():void{
    this.playlistService.changeTitle(this.title, this.playlists[this.index].playlistId).subscribe(data =>{
      this.refreshPlaylists();
    });
    this.isEdited=false;
    this.title = "";
  }

  deletePlaylist(index:number):void{
    this.playlistItemService.deleteAllPlaylistItem(this.playlists[index].title, this.userId).subscribe(data => {
      console.log(data)
      this.refreshPlaylistsItem(index);
     });
    this.playlistService.deletePlaylist(this.playlists[index].playlistId)
     .subscribe(data => {
      this.refreshPlaylists();
    })
  }

  activeDeleteItemButton(index:number):void{
    this.itemIndex = index;
    this.itemIsClicked = true;
  }

  deletePlaylistItem():void{
    this.playlistItemService.deletePlaylistItem(this.playlistItems[this.itemIndex].itemId)
    .subscribe(data => {
     console.log(data)
     this.refreshPlaylistsItem(this.index);
    })
  }
}

