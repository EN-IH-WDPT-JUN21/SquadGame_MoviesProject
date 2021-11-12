package com.ironhack.playlistservice.converter;

import com.ironhack.playlistservice.dao.PlaylistItem;
import com.ironhack.playlistservice.dto.PlaylistItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaylistItemConverter {

    public PlaylistItem dtoToEntity(PlaylistItemDto playlistItemDto){
        ModelMapper mapper =new ModelMapper();
        PlaylistItem playlistItem = mapper.map(playlistItemDto, PlaylistItem.class);
        return playlistItem;
    }
    public List<PlaylistItemDto> entityToDto(List<PlaylistItem> playlistItem) {
        return playlistItem.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public PlaylistItemDto entityToDto(PlaylistItem playlistItem){
        ModelMapper mapper =new ModelMapper();
        PlaylistItemDto playlistItemDto = mapper.map(playlistItem, PlaylistItemDto.class);
        return playlistItemDto;
    }
}
