package com.ironhack.playlistservice.converter;

import com.ironhack.playlistservice.dao.Playlist;
import com.ironhack.playlistservice.dto.PlaylistDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaylistConverter {

    public Playlist dtoToEntity(PlaylistDto playlistDto){
        ModelMapper mapper =new ModelMapper();
        Playlist playlist = mapper.map(playlistDto, Playlist.class);
        return playlist;
    }
    public List<PlaylistDto> entityToDto(List<Playlist> playlist) {
        return playlist.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public PlaylistDto entityToDto(Playlist playlist){
        ModelMapper mapper =new ModelMapper();
        PlaylistDto playlistDto = mapper.map(playlist, PlaylistDto.class);
        return playlistDto;
    }
}
