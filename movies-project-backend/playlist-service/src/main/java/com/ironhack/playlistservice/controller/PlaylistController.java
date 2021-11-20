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
@CrossOrigin("http://localhost:4200")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<PlaylistDto> getAllPlaylists( @RequestHeader (name="Authorization") String token){
        return playlistService.getAllPlaylists(token);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    private List<PlaylistDto> getPlaylistsByUserId(@RequestHeader (name="Authorization") String token){return playlistService.getPlaylistsByUserId(token);}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private PlaylistDto getPlaylistById(@PathVariable Long id, @RequestHeader (name="Authorization") String token){
        return playlistService.getPlaylistById(id, token);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private PlaylistDto createPlaylist(@RequestBody PlaylistDto playlistDto, @RequestHeader (name="Authorization") String token){
        System.out.println("Check body" + playlistDto);
        return playlistService.createPlaylist(playlistDto, token);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private PlaylistDto changeTitle(@RequestBody TitleDto titleDto, @PathVariable Long id, @RequestHeader (name="Authorization") String token){
        return playlistService.updatePlaylist(titleDto, id, token);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePlaylist(@PathVariable Long id, @RequestHeader (name="Authorization") String token){
        playlistService.deletePlaylist(id, token);
    }
}
