package com.ironhack.playlistservice.controller;

import com.ironhack.playlistservice.dto.PlaylistItemDto;
import com.ironhack.playlistservice.service.PlaylistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist-item")
@CrossOrigin("http://localhost:4200")
public class PlaylistItemController {

    @Autowired
    private PlaylistItemService playlistItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<PlaylistItemDto> getAllPlaylistItem() {
        return playlistItemService.getAllPlaylistItem();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private PlaylistItemDto getPlaylistItemById(@PathVariable Long id, @RequestHeader (name="Authorization") String token) {
        return playlistItemService.getPlaylistItemById(id, token);
    }

    @GetMapping("/playlist-title/{playlistTitle}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    private List<PlaylistItemDto> getPlaylistItemsByPlaylistTitleAndUserId(@PathVariable Long userId, @PathVariable String playlistTitle, @RequestHeader (name="Authorization") String token) {
        return playlistItemService.getPlaylistItemsByPlaylistTitleAndUserId(userId, playlistTitle, token);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private PlaylistItemDto createPlaylistItem(@RequestBody PlaylistItemDto playlistItemDto, @RequestHeader (name="Authorization") String token) {
        return playlistItemService.createPlaylistItem(playlistItemDto, token);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePlaylistItem(@PathVariable Long id,  @RequestHeader (name="Authorization") String token) {
        playlistItemService.deletePlaylistItem(id, token);
    }

    @DeleteMapping("/{userId}/{playlistTitle}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteAllPlaylistItem(@PathVariable Long userId, @PathVariable String playlistTitle,  @RequestHeader (name="Authorization") String token) {
        playlistItemService.deleteAllPlaylistItem(userId, playlistTitle, token);
    }
}
