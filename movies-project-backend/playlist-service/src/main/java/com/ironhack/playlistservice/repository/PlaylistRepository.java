package com.ironhack.playlistservice.repository;

import com.ironhack.playlistservice.dao.Playlist;
import com.ironhack.playlistservice.dao.PlaylistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUserId(Long userId);
}
