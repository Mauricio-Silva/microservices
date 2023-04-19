package br.com.ifms.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifms.microservices.dto.RecordCompanyDTO;
import br.com.ifms.microservices.model.RecordCompany;
import br.com.ifms.microservices.service.RecordCompanyService;

@RestController
@RequestMapping(value = "/api/recordCompany")
public class RecordCompanyController {

    @Autowired
    RecordCompanyService recordCompanyService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The recordCompany arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<RecordCompany>> listAll() {
        return ResponseEntity.ok(this.recordCompanyService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RecordCompany> getRecordCompany(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.recordCompanyService.getOneById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<RecordCompany>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.recordCompanyService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.recordCompanyService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecordCompany(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.recordCompanyService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<RecordCompany> saveRecordCompany(@RequestBody RecordCompanyDTO recordCompanyDTO) {
        return ResponseEntity.ok(this.recordCompanyService.save(recordCompanyDTO));
    }


    @PutMapping()
    public ResponseEntity<RecordCompany> updateRecordCompany(@RequestBody RecordCompanyDTO recordCompanyDTO) {
        return ResponseEntity.ok(this.recordCompanyService.update(recordCompanyDTO));
    }
}
