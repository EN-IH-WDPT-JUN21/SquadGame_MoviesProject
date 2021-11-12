package com.ironhack.playlistservice.controller;

import com.ironhack.playlistservice.dto.PlaylistDto;
import com.ironhack.playlistservice.dto.PlaylistItemDto;
import com.ironhack.playlistservice.service.PlaylistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping("/playlist-item")
public class PlaylistItemController {

    @Autowired
    private PlaylistItemService playlistItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<PlaylistItemDto> getAllPlaylistItem(){
        return playlistItemService.getAllPlaylistItem();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private PlaylistItemDto getPlaylistItemById(@PathVariable Long id){
        return playlistItemService.getPlaylistItemById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private PlaylistItemDto createPlaylistItem(@RequestBody PlaylistItemDto playlistItemDto){
        return playlistItemService.createPlaylistItem(playlistItemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePlaylistItem(@PathVariable Long id){
        playlistItemService.deletePlaylistItem(id);
    }
}
