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
    private String id;
    private String title;
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    public PlaylistItem(String movieId, String title, String description, String imageUrl) {
        this.id = movieId;
        this.title = title;
        this.description = description;
        this.image = imageUrl;
    }

    public PlaylistItem(String movieId, Playlist playlist) {
        this.id = movieId;
        this.playlist = playlist;
    }
}
