package com.ironhack.playlistservice.service;

import com.ironhack.playlistservice.converter.PlaylistConverter;
import com.ironhack.playlistservice.converter.PlaylistItemConverter;
import com.ironhack.playlistservice.dao.Playlist;
import com.ironhack.playlistservice.dao.PlaylistItem;
import com.ironhack.playlistservice.dto.PlaylistItemDto;
import com.ironhack.playlistservice.repository.PlaylistItemRepository;
import com.ironhack.playlistservice.repository.PlaylistRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@Service
public class PlaylistItemService {

    @Autowired
    Environment environment;

    @Autowired
    private PlaylistItemRepository playlistItemRepository;

    @Autowired
    private PlaylistItemConverter playlistItemConverter;

    public List<PlaylistItemDto> getAllPlaylistItem() {
        return playlistItemConverter.entityToDto(playlistItemRepository.findAll());
    }

    public PlaylistItemDto getPlaylistItemById(Long id, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        PlaylistItem playlistItem = playlistItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist item not found for this id : " + id));
        if (!tokenSubject.equals(playlistItem.getPlaylist().getUserId())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        }else{
            return playlistItemConverter.entityToDto(playlistItem);
        }
    }

    public List<PlaylistItemDto> getPlaylistItemsByPlaylistTitleAndUserId(Long userId, String playlistTitle, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        if (!tokenSubject.equals(userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        }else{
            if (playlistTitle != null) {
                return playlistItemConverter.entityToDto(playlistItemRepository.findByPlaylistTitleAndUserId(playlistTitle.toLowerCase(), userId));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Title should be not null");
            }
        }
    }

    public PlaylistItemDto createPlaylistItem(PlaylistItemDto playlistItemDto, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        if (!tokenSubject.equals(playlistItemDto.getPlaylist().getUserId())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        }else{
            List<PlaylistItem> playlistItemList = playlistItemRepository.findByPlaylistTitle(playlistItemDto.getPlaylist().getTitle());
            boolean isMovieDuplicated = false;
            for (PlaylistItem playlistItem : playlistItemList) {
                if (playlistItem.getId() == playlistItemDto.getId()) {
                    isMovieDuplicated = true;
                    break;
                }
            }
            if (isMovieDuplicated == false) {
                if (playlistItemList.size() < 10) {
                    PlaylistItem playlistItem = playlistItemConverter.dtoToEntity(playlistItemDto);
                    playlistItemRepository.save(playlistItem);
                    return playlistItemConverter.entityToDto(playlistItem);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The playlist cannot contain more than 10 movies");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The playlist cannot contain the same movies");
            }
        }
    }

    public void deletePlaylistItem(Long id, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        PlaylistItem playlistItem =playlistItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist item not found for this id : " + id));

        if (!tokenSubject.equals(playlistItem.getPlaylist().getUserId())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        } else {
            playlistItemRepository.deleteById(id);
        }
    }

    public void deleteAllPlaylistItem(Long userId, String playlistTitle, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        if (!tokenSubject.equals(userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        } else {
            List<PlaylistItem> playlistItemList = playlistItemRepository.findByPlaylistTitleAndUserId(playlistTitle, userId);
            if(playlistItemList != null){
                for(PlaylistItem playlistItem: playlistItemList){
                    playlistItemRepository.deleteById(playlistItem.getItemId());
                }
            } else {
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist item not found for this userid: " + userId + " and title: " + playlistTitle);
            }
        }
    }
}
