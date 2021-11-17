package com.ironhack.playlistservice.dto;

import com.ironhack.playlistservice.dao.Playlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistItemDto {
    private Long itemId;

    private String movieId;
    private Playlist playlist;
}
