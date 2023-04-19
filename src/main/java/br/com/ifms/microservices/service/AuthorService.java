package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.AuthorDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.Author;
import br.com.ifms.microservices.repository.AuthorRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class AuthorService {
    
    @Autowired
    AuthorRepository authorRepository;


    public long getCount() {
        return this.authorRepository.count();
    }


    public List<Author> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no authors in the database");
        }
        return this.authorRepository.findAll();
    }


    public Author getOneById(long id) {
        Optional<Author> author = this.authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new NotFoundException("Author not found in the database");
        }
    }   


    public List<Author> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no authors in the database");
        }
        return this.authorRepository.findByName(name);
    }


    public Author save(AuthorDTO authorDTO) {
        try {
            Author author = new Author();
            BeanUtils.copyProperties(authorDTO, author);
            return this.authorRepository.save(author);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the author");
        }
    }

    
    public String delete(long id) {
        Optional<Author> author = this.authorRepository.findById(id);
        if (author.isPresent()) {
            this.authorRepository.deleteById(id);
            return "The author was deleted successfully";
        } else {
            throw new NotFoundException("Author not found in the database");
        }
    }


    public Author update(AuthorDTO authorDTO) {
        if (authorDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        Author author = getOneById(authorDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(authorDTO);
        BeanUtils.copyProperties(authorDTO, author, ignoreAttributes);
        try {
            return this.authorRepository.save(author);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the author");
        }
    }
}
