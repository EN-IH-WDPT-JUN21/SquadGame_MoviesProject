package com.ironhack.playlistservice.repository;

import com.ironhack.playlistservice.dao.PlaylistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Long> {
}
