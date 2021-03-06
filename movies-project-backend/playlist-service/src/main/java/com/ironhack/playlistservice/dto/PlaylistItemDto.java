package com.ironhack.playlistservice.dto;

import com.ironhack.playlistservice.dao.Playlist;
import com.sun.istack.NotNull;
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
    @NotNull
    private String id;
    private String title;
    private String description;
    private String image;
    @NotNull
    private Playlist playlist;
}
