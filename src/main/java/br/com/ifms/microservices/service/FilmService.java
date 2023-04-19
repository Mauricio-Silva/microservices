package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.FilmDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.Film;
import br.com.ifms.microservices.repository.FilmRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;


    public long getCount() {
        return this.filmRepository.count();
    }


    public List<Film> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no films in the database");
        }
        return this.filmRepository.findAll();
    }


    public Film getOneById(long id) {
        Optional<Film> film = this.filmRepository.findById(id);
        if (film.isPresent()) {
            return film.get();
        } else {
            throw new NotFoundException("Film not found in the database");
        }
    }   


    public List<Film> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no films in the database");
        }
        return this.filmRepository.findByName(name);
    }


    public Film save(FilmDTO filmDTO) {
        try {
            Film film = new Film();
            BeanUtils.copyProperties(filmDTO, film);
            return this.filmRepository.save(film);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the film");
        }
    }


    public Film saveEntity(Film film) {
        try {
            return this.filmRepository.save(film);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the film");
        }
    }

    
    public String delete(long id) {
        Optional<Film> film = this.filmRepository.findById(id);
        if (film.isPresent()) {
            this.filmRepository.deleteById(id);
            return "The film was deleted successfully";
        } else {
            throw new NotFoundException("Film not found in the database");
        }
    }


    public Film update(FilmDTO filmDTO) {
        if (filmDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        Film film = getOneById(filmDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(filmDTO);
        BeanUtils.copyProperties(filmDTO, film, ignoreAttributes);
        try {
            return this.filmRepository.save(film);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the film");
        }
    }
}
