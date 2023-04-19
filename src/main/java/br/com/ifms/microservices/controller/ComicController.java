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

import br.com.ifms.microservices.dto.ComicDTO;
import br.com.ifms.microservices.model.Comic;
import br.com.ifms.microservices.service.ComicService;

@RestController
@RequestMapping(value = "/api/comic")
public class ComicController {

    @Autowired
    ComicService comicService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The comic arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Comic>> listAll() {
        return ResponseEntity.ok(this.comicService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Comic> getComic(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.comicService.getOneById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Comic>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.comicService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.comicService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComic(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.comicService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<Comic> saveComic(@RequestBody ComicDTO comicDTO) {
        return ResponseEntity.ok(this.comicService.save(comicDTO));
    }


    @PutMapping()
    public ResponseEntity<Comic> updateComic(@RequestBody ComicDTO comicDTO) {
        return ResponseEntity.ok(this.comicService.update(comicDTO));
    }
}
