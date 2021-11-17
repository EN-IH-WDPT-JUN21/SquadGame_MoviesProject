package com.ironhack.playlistservice.dao;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Long playlistId;

    @NotNull
    private Long userId;

    @NotNull
    private String title;
    private boolean isFull;

    //@OneToMany(mappedBy = "playlist")
    //private Set<PlaylistItem> playlistItems;

    public Playlist(String title) {
        this.title = title;
        this.isFull = false;
    }
}
