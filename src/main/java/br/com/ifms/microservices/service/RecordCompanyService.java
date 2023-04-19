package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.RecordCompanyDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.RecordCompany;
import br.com.ifms.microservices.repository.RecordCompanyRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class RecordCompanyService {

    @Autowired
    RecordCompanyRepository recordCompanyRepository;


    public long getCount() {
        return this.recordCompanyRepository.count();
    }


    public List<RecordCompany> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no record companies in the database");
        }
        return this.recordCompanyRepository.findAll();
    }


    public RecordCompany getOneById(long id) {
        Optional<RecordCompany> recordCompany = this.recordCompanyRepository.findById(id);
        if (recordCompany.isPresent()) {
            return recordCompany.get();
        } else {
            throw new NotFoundException("RecordCompany not found in the database");
        }
    }   


    public List<RecordCompany> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no record companies in the database");
        }
        return this.recordCompanyRepository.findByName(name);
    }


    public RecordCompany save(RecordCompanyDTO recordCompanyDTO) {
        try {
            RecordCompany recordCompany = new RecordCompany();
            BeanUtils.copyProperties(recordCompanyDTO, recordCompany);
            return this.recordCompanyRepository.save(recordCompany);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the recordCompany");
        }
    }

    
    public String delete(long id) {
        Optional<RecordCompany> recordCompany = this.recordCompanyRepository.findById(id);
        if (recordCompany.isPresent()) {
            this.recordCompanyRepository.deleteById(id);
            return "The recordCompany was deleted successfully";
        } else {
            throw new NotFoundException("RecordCompany not found in the database");
        }
    }


    public RecordCompany update(RecordCompanyDTO recordCompanyDTO) {
        if (recordCompanyDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        RecordCompany recordCompany = getOneById(recordCompanyDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(recordCompanyDTO);
        BeanUtils.copyProperties(recordCompanyDTO, recordCompany, ignoreAttributes);  
        try {
            return this.recordCompanyRepository.save(recordCompany);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the recordCompany");
        }
    }    
}
