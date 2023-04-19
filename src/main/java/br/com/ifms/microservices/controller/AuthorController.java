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

import br.com.ifms.microservices.dto.AuthorDTO;
import br.com.ifms.microservices.model.Author;
import br.com.ifms.microservices.service.AuthorService;

@RestController
@RequestMapping(value = "/api/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The author arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Author>> listAll() {
        return ResponseEntity.ok(this.authorService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.authorService.getOneById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Author>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.authorService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.authorService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.authorService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<Author> saveAuthor(@RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(this.authorService.save(authorDTO));
    }


    @PutMapping()
    public ResponseEntity<Author> updateAuthor(@RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(this.authorService.update(authorDTO));
    }
}
