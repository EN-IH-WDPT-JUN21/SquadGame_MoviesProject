package com.ironhack.playlistservice.controller;

import com.ironhack.playlistservice.dto.PlaylistDto;
import com.ironhack.playlistservice.dto.TitleDto;
import com.ironhack.playlistservice.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<PlaylistDto> getAllPlaylists(){
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    private List<PlaylistDto> getPlaylistsByUserId(@PathVariable Long id){return playlistService.getPlaylistsByUserId(id);}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private PlaylistDto getPlaylistById(@PathVariable Long id){
        return playlistService.getPlaylistById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private PlaylistDto createPlaylist(@RequestBody PlaylistDto playlistDto){
        return playlistService.createPlaylist(playlistDto);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private PlaylistDto changeTitle(@RequestBody TitleDto titleDto, @PathVariable Long id){
        return playlistService.updatePlaylist(titleDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePlaylist(@PathVariable Long id){
        playlistService.deletePlaylist(id);
    }
}
