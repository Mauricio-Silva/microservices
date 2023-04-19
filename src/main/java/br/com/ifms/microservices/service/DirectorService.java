package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.DirectorDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.Director;
import br.com.ifms.microservices.repository.DirectorRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class DirectorService {
    
    @Autowired
    DirectorRepository directorRepository;


    public long getCount() {
        return this.directorRepository.count();
    }


    public List<Director> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no directors in the database");
        }
        return this.directorRepository.findAll();
    }


    public Director getOneById(long id) {
        Optional<Director> director = this.directorRepository.findById(id);
        if (director.isPresent()) {
            return director.get();
        } else {
            throw new NotFoundException("Director not found in the database");
        }
    }   


    public List<Director> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no directors in the database");
        }
        return this.directorRepository.findByName(name);
    }


    public Director save(DirectorDTO directorDTO) {
        try {
            Director director = new Director();
            BeanUtils.copyProperties(directorDTO, director);
            return this.directorRepository.save(director);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the director");
        }
    }

    
    public String delete(long id) {
        Optional<Director> director = this.directorRepository.findById(id);
        if (director.isPresent()) {
            this.directorRepository.deleteById(id);
            return "The director was deleted successfully";
        } else {
            throw new NotFoundException("Director not found in the database");
        }
    }


    public Director update(DirectorDTO directorDTO) {
        if (directorDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        Director director = getOneById(directorDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(directorDTO);
        BeanUtils.copyProperties(directorDTO, director, ignoreAttributes);
        try {
            return this.directorRepository.save(director);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the director");
        }
    }
}
