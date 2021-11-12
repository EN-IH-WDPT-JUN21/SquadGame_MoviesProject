package com.ironhack.playlistservice.service;

import com.ironhack.playlistservice.converter.PlaylistConverter;
import com.ironhack.playlistservice.converter.PlaylistItemConverter;
import com.ironhack.playlistservice.dao.Playlist;
import com.ironhack.playlistservice.dao.PlaylistItem;
import com.ironhack.playlistservice.dto.PlaylistDto;
import com.ironhack.playlistservice.dto.PlaylistItemDto;
import com.ironhack.playlistservice.dto.TitleDto;
import com.ironhack.playlistservice.repository.PlaylistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistItemService {

    @Autowired
    private PlaylistItemRepository playlistItemRepository;

    @Autowired
    private PlaylistItemConverter playlistItemConverter;

    @Autowired
    private PlaylistConverter playlistConverter;


    public List<PlaylistItemDto> getAllPlaylistItem() {
        return playlistItemConverter.entityToDto(playlistItemRepository.findAll());
    }

    public PlaylistItemDto getPlaylistItemById(Long id) {
        PlaylistItemDto playlistItemDto = playlistItemConverter.entityToDto(playlistItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist item not found for this id : " + id)));
        return playlistItemDto;
    }

    public PlaylistItemDto createPlaylistItem(PlaylistItemDto playlistItemDto) {
        PlaylistItem playlistItem = playlistItemConverter.dtoToEntity(playlistItemDto);
        playlistItemRepository.save(playlistItem);
        return playlistItemConverter.entityToDto(playlistItem);
    }

    public void deletePlaylistItem(Long id) {
        PlaylistItem playlistItem = playlistItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist item not found for this id : " + id));
        playlistItemRepository.deleteById(id);
    }
}
