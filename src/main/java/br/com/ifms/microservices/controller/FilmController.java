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

import br.com.ifms.microservices.dto.FilmDTO;
import br.com.ifms.microservices.model.Film;
import br.com.ifms.microservices.service.FilmService;

@RestController
@RequestMapping(value = "/api/film")
public class FilmController {

    @Autowired
    FilmService filmService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The film arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Film>> listAll() {
        return ResponseEntity.ok(this.filmService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.filmService.getOneById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Film>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.filmService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.filmService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.filmService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<Film> saveFilm(@RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(this.filmService.save(filmDTO));
    }


    @PutMapping()
    public ResponseEntity<Film> updateFilm(@RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(this.filmService.update(filmDTO));
    }
}
