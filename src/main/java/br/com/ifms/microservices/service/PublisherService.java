package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.PublisherDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.Publisher;
import br.com.ifms.microservices.repository.PublisherRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;


    public long getCount() {
        return this.publisherRepository.count();
    }


    public List<Publisher> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no publishers in the database");
        }
        return this.publisherRepository.findAll();
    }


    public Publisher getOneById(long id) {
        Optional<Publisher> publisher = this.publisherRepository.findById(id);
        if (publisher.isPresent()) {
            return publisher.get();
        } else {
            throw new NotFoundException("Publisher not found in the database");
        }
    }


    public List<Publisher> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no publishers in the database");
        }
        return this.publisherRepository.findByName(name);
    }


    public Publisher save(PublisherDTO publisherDTO) {
        try {
            Publisher publisher = new Publisher();
            BeanUtils.copyProperties(publisherDTO, publisher);
            return this.publisherRepository.save(publisher);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the publisher");
        }
    }

    
    public String delete(long id) {
        Optional<Publisher> publisher = this.publisherRepository.findById(id);
        if (publisher.isPresent()) {
            this.publisherRepository.deleteById(id);
            return "The publisher was deleted successfully";
        } else {
            throw new NotFoundException("Publisher not found in the database");
        }
    }


    public Publisher update(PublisherDTO publisherDTO) {
        if (publisherDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        Publisher publisher = getOneById(publisherDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(publisherDTO);
        BeanUtils.copyProperties(publisherDTO, publisher, ignoreAttributes);
        try {
            return this.publisherRepository.save(publisher);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the publisher");
        }
    }
}
