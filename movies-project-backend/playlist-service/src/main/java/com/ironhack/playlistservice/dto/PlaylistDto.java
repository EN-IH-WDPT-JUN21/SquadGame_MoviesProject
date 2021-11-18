package com.ironhack.playlistservice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {
    private Long playlistId;

    @NotNull
    private Long userId;

    @NotNull
    private String title;
    private boolean isFull;

    public PlaylistDto(Long userId, String title, boolean isFull) {
        this.userId = userId;
        this.title = title;
        this.isFull = isFull;
    }

    //private Set<Long> moviesId;
}
