package com.ironhack.playlistservice.service;

import com.ironhack.playlistservice.converter.PlaylistConverter;
import com.ironhack.playlistservice.dao.Playlist;
import com.ironhack.playlistservice.dto.PlaylistDto;
import com.ironhack.playlistservice.dto.TitleDto;
import com.ironhack.playlistservice.repository.PlaylistRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    Environment environment;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistConverter playlistConverter;

    public List<PlaylistDto> getAllPlaylists(String token) {
        token = token.replace("Bearer","");
        try {
            Long tokenSubject = Long.parseLong(Jwts.parser()
                    .setSigningKey(environment.getProperty("token.secret"))
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        }
        return playlistConverter.entityToDto(playlistRepository.findAll());
    }

    public List<PlaylistDto> getPlaylistsByUserId(Long id, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());

        if (!tokenSubject.equals(id)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        }else{
            return playlistConverter.entityToDto(playlistRepository.findByUserId(id));
        }
    }

    public PlaylistDto getPlaylistById(Long id, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist not found for this id : " + id));
        if (!tokenSubject.equals(playlist.getUserId())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        } else {
            return playlistConverter.entityToDto(playlist);
        }
    }

    public PlaylistDto createPlaylist(PlaylistDto playlistDto, String token) {
        token = token.replace("Bearer","");
        try {
            Long tokenSubject = Long.parseLong(Jwts.parser()
                    .setSigningKey(environment.getProperty("token.secret"))
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        }
        Playlist playlist = playlistConverter.dtoToEntity(playlistDto);
        playlistRepository.save(playlist);
        return playlistConverter.entityToDto(playlist);
    }

    public PlaylistDto updatePlaylist(TitleDto titleDto, Long id, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist not found for this id : " + id));

        if (!tokenSubject.equals(playlist.getUserId())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        } else {
            if (titleDto != null) {
                try {
                    playlist.setTitle(titleDto.getTitle());
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title can not be blank");
                }
            }
            playlistRepository.save(playlist);
            return playlistConverter.entityToDto(playlist);
        }
    }

    public void deletePlaylist(Long id, String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist not found for this id : " + id));

        if (!tokenSubject.equals(playlist.getUserId())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
        } else {
            playlistRepository.deleteById(id);
        }
    }
}
