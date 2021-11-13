package com.ironhack.playlistservice.service;

import com.ironhack.playlistservice.converter.PlaylistConverter;
import com.ironhack.playlistservice.dao.Playlist;
import com.ironhack.playlistservice.dto.PlaylistDto;
import com.ironhack.playlistservice.dto.TitleDto;
import com.ironhack.playlistservice.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistConverter playlistConverter;

    public List<PlaylistDto> getAllPlaylists() {
        return playlistConverter.entityToDto(playlistRepository.findAll());
    }

    public PlaylistDto getPlaylistById(Long id) {
        PlaylistDto playlistDto = playlistConverter.entityToDto(playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist not found for this id : " + id)));
        return playlistDto;
    }

    public PlaylistDto createPlaylist(PlaylistDto playlistDto) {
        Playlist playlist = playlistConverter.dtoToEntity(playlistDto);
        playlistRepository.save(playlist);
        return playlistConverter.entityToDto(playlist);
    }

    public PlaylistDto updatePlaylist(TitleDto titleDto, Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist not found for this id : " + id));
        if (titleDto != null) {
                try {
                    playlist.setTitle(titleDto.getTitle());
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title can not be blank");
                }
            }
        playlistRepository.save(playlist);
        return playlistConverter.entityToDto(playlist);
    }

    public void deletePlaylist(Long id) {
        playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist not found for this id : " + id));
        playlistRepository.deleteById(id);
    }

    public List<PlaylistDto> getPlaylistsByUserId(Long id) {
        return playlistConverter.entityToDto(playlistRepository.findByUserId(id));
    }
}
