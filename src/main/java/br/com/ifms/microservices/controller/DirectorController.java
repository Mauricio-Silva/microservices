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

import br.com.ifms.microservices.dto.DirectorDTO;
import br.com.ifms.microservices.model.Director;
import br.com.ifms.microservices.service.DirectorService;

@RestController
@RequestMapping(value = "/api/director")
public class DirectorController {

    @Autowired
    DirectorService directorService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The director arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Director>> listAll() {
        return ResponseEntity.ok(this.directorService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirector(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.directorService.getOneById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Director>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.directorService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.directorService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.directorService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<Director> saveDirector(@RequestBody DirectorDTO directorDTO) {
        return ResponseEntity.ok(this.directorService.save(directorDTO));
    }


    @PutMapping()
    public ResponseEntity<Director> updateDirector(@RequestBody DirectorDTO directorDTO) {
        return ResponseEntity.ok(this.directorService.update(directorDTO));
    }
}
