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

import br.com.ifms.microservices.dto.BookDTO;
import br.com.ifms.microservices.model.Book;
import br.com.ifms.microservices.service.BookService;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The book arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Book>> listAll() {
        return ResponseEntity.ok(this.bookService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.bookService.getOneById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Book>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.bookService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.bookService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.bookService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<Book> saveBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(this.bookService.save(bookDTO));
    }


    @PutMapping()
    public ResponseEntity<Book> updateBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(this.bookService.update(bookDTO));
    }
}
