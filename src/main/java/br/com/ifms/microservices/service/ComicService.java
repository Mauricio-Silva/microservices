package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.ComicDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.Comic;
import br.com.ifms.microservices.repository.ComicRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class ComicService {

    @Autowired
    ComicRepository comicRepository;


    public long getCount() {
        return this.comicRepository.count();
    }


    public List<Comic> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no comics in the database");
        }
        return this.comicRepository.findAll();
    }


    public Comic getOneById(long id) {
        Optional<Comic> comic = this.comicRepository.findById(id);
        if (comic.isPresent()) {
            return comic.get();
        } else {
            throw new NotFoundException("Comic not found in the database");
        }
    }   


    public List<Comic> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no comics in the database");
        }
        return this.comicRepository.findByName(name);
    }


    public Comic save(ComicDTO comicDTO) {
        try {
            Comic comic = new Comic();
            BeanUtils.copyProperties(comicDTO, comic);
            return this.comicRepository.save(comic);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the comic");
        }
    }

    
    public String delete(long id) {
        Optional<Comic> comic = this.comicRepository.findById(id);
        if (comic.isPresent()) {
            this.comicRepository.deleteById(id);
            return "The comic was deleted successfully";
        } else {
            throw new NotFoundException("Comic not found in the database");
        }
    }


    public Comic update(ComicDTO comicDTO) {
        if (comicDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        Comic comic = getOneById(comicDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(comicDTO);
        BeanUtils.copyProperties(comicDTO, comic, ignoreAttributes);
        try {
            return this.comicRepository.save(comic);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the comic");
        }
    }    
}
