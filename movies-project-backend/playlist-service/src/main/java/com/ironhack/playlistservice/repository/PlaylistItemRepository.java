package com.ironhack.playlistservice.repository;

import com.ironhack.playlistservice.dao.PlaylistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Long> {
    @Query(value = "SELECT * FROM playlist_item pi INNER JOIN playlist p ON pi.playlist_id = p.playlist_id WHERE p.title = :playlistTitle", nativeQuery = true)
    List<PlaylistItem> findByPlaylistTitle(@Param("playlistTitle") String playlistTitle);
}
