package com.ironhack.playlistservice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {
    private Long playlistId;

    @NotNull
    private String title;
    private boolean isFull;
    //private Set<Long> moviesId;
}
