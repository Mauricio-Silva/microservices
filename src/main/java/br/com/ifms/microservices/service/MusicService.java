package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.MusicDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.Music;
import br.com.ifms.microservices.repository.MusicRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class MusicService {
    
    @Autowired
    MusicRepository musicRepository;


    public long getCount() {
        return this.musicRepository.count();
    }


    public List<Music> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no musics in the database");
        }
        return this.musicRepository.findAll();
    }


    public Music getOneById(long id) {
        Optional<Music> music = this.musicRepository.findById(id);
        if (music.isPresent()) {
            return music.get();
        } else {
            throw new NotFoundException("Music not found in the database");
        }
    }   


    public List<Music> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no musics in the database");
        }
        return this.musicRepository.findByName(name);
    }


    public Music save(MusicDTO musicDTO) {
        try {
            Music music = new Music();
            BeanUtils.copyProperties(musicDTO, music);
            return this.musicRepository.save(music);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the music");
        }
    }

    
    public String delete(long id) {
        Optional<Music> music = this.musicRepository.findById(id);
        if (music.isPresent()) {
            this.musicRepository.deleteById(id);
            return "The music was deleted successfully";
        } else {
            throw new NotFoundException("Music not found in the database");
        }
    }


    public Music update(MusicDTO musicDTO) {
        if (musicDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        Music music = getOneById(musicDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(musicDTO);
        BeanUtils.copyProperties(musicDTO, music, ignoreAttributes); 
        try {
            return this.musicRepository.save(music);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the music");
        }
    }
}
