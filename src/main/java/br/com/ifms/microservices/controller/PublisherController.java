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

import br.com.ifms.microservices.dto.PublisherDTO;
import br.com.ifms.microservices.model.Publisher;
import br.com.ifms.microservices.service.PublisherService;

@RestController
@RequestMapping(value = "/api/publisher")
public class PublisherController {

    @Autowired
    PublisherService publisherService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The publisher arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Publisher>> listAll() {
        return ResponseEntity.ok(this.publisherService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisher(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.publisherService.getOneById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Publisher>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.publisherService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.publisherService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.publisherService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<Publisher> savePublisher(@RequestBody PublisherDTO publisherDTO) {
        return ResponseEntity.ok(this.publisherService.save(publisherDTO));
    }


    @PutMapping()
    public ResponseEntity<Publisher> updatePublisher(@RequestBody PublisherDTO publisherDTO) {
        return ResponseEntity.ok(this.publisherService.update(publisherDTO));
    }
}
