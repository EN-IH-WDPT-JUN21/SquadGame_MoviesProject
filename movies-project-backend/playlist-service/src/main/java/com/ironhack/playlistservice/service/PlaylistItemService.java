package com.ironhack.playlistservice.service;

import com.ironhack.playlistservice.converter.PlaylistConverter;
import com.ironhack.playlistservice.converter.PlaylistItemConverter;
import com.ironhack.playlistservice.dao.PlaylistItem;
import com.ironhack.playlistservice.dto.PlaylistItemDto;
import com.ironhack.playlistservice.repository.PlaylistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist item not found for this id : " + id)));
        return playlistItemDto;
    }

    public List<PlaylistItemDto> getPlaylistItemsByPlaylistTitle(String playlistTitle) {
        if (playlistTitle != null) {
            return playlistItemConverter.entityToDto(playlistItemRepository.findByPlaylistTitle(playlistTitle));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Title should be not null");
        }
    }

    public PlaylistItemDto createPlaylistItem(PlaylistItemDto playlistItemDto) {
        List<PlaylistItem> playlistItemList = playlistItemRepository.findByPlaylistTitle(playlistItemDto.getPlaylist().getTitle());
        boolean isMovieDuplicated = false;
        for (PlaylistItem playlistItem : playlistItemList) {
            if (playlistItem.getMovieId() == playlistItemDto.getMovieId()) {
                isMovieDuplicated = true;
                break;
            }
        }

        if (isMovieDuplicated == false) {
            if (playlistItemList.size() < 10) {
                PlaylistItem playlistItem = playlistItemConverter.dtoToEntity(playlistItemDto);
                playlistItemRepository.save(playlistItem);
                return playlistItemConverter.entityToDto(playlistItem);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The playlist cannot contain more than 10 movies");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The playlist cannot contain the same movies");
        }
    }

    public void deletePlaylistItem(Long id) {
        playlistItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist item not found for this id : " + id));
        playlistItemRepository.deleteById(id);
    }
}
