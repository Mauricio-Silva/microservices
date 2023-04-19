package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.SeriesDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.Series;
import br.com.ifms.microservices.repository.SeriesRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class SeriesService {
    
    @Autowired
    SeriesRepository seriesRepository;


    public long getCount() {
        return this.seriesRepository.count();
    }


    public List<Series> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no series in the database");
        }
        return this.seriesRepository.findAll();
    }


    public Series getOneById(long id) {
        Optional<Series> series = this.seriesRepository.findById(id);
        if (series.isPresent()) {
            return series.get();
        } else {
            throw new NotFoundException("Series not found in the database");
        }
    }   


    public List<Series> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no series in the database");
        }
        return this.seriesRepository.findByName(name);
    }


    public Series save(SeriesDTO seriesDTO) {
        try {
            Series series = new Series();
            BeanUtils.copyProperties(seriesDTO, series);
            return this.seriesRepository.save(series);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the series");
        }
    }

    
    public String delete(long id) {
        Optional<Series> series = this.seriesRepository.findById(id);
        if (series.isPresent()) {
            this.seriesRepository.deleteById(id);
            return "The series was deleted successfully";
        } else {
            throw new NotFoundException("Series not found in the database");
        }
    }


    public Series update(SeriesDTO seriesDTO) {
        if (seriesDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        Series series = getOneById(seriesDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(seriesDTO);
        BeanUtils.copyProperties(seriesDTO, series, ignoreAttributes); 
        try {
            return this.seriesRepository.save(series);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the series");
        }
    }
}
