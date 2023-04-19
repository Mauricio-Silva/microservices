package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.SingerDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.Singer;
import br.com.ifms.microservices.repository.SingerRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class SingerService {

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    FilmService filmService;


    public long getCount() {
        return this.singerRepository.count();
    }


    public List<Singer> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no singers in the database");
        }
        return this.singerRepository.findAll();
    }


    public Singer getOneById(long id) {
        Optional<Singer> singer = this.singerRepository.findById(id);
        if (singer.isPresent()) {
            return singer.get();
        } else {
            throw new NotFoundException("Singer not found in the database");
        }
    } 


    public List<Singer> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no singers in the database");
        }
        return this.singerRepository.findByName(name);
    }   


    public Singer save(SingerDTO singerDTO) {
        try {
            Singer singer = new Singer();
            BeanUtils.copyProperties(singerDTO, singer);
            return this.singerRepository.save(singer);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the singer");
        }
    }


    public Singer saveEntity(Singer singer) {
        try {
            return this.singerRepository.save(singer);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the singer");
        }
    }

    
    public String delete(long id) {
        Optional<Singer> singer = this.singerRepository.findById(id);
        if (singer.isPresent()) {
            this.singerRepository.deleteById(id);
            return "The singer was deleted successfully";
        } else {
            throw new NotFoundException("Singer not found in the database");
        }
    }


    public Singer update(SingerDTO singerDTO) {
        if (singerDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        Singer singer = getOneById(singerDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(singerDTO);
        BeanUtils.copyProperties(singerDTO, singer, ignoreAttributes);
        try {
            return this.singerRepository.save(singer);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the singer");
        }
    }
}
