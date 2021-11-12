package com.ironhack.playlistservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "movie_id")
    private Long movieId;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    public PlaylistItem(Long movieId) {
        this.movieId = movieId;
    }

    public PlaylistItem(Long movieId, Playlist playlist) {
        this.movieId = movieId;
        this.playlist = playlist;
    }
}
