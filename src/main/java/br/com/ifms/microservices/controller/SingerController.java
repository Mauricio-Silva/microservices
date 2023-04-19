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

import br.com.ifms.microservices.dto.SingerDTO;
import br.com.ifms.microservices.model.Singer;
import br.com.ifms.microservices.service.SingerService;

@RestController
@RequestMapping(value = "/api/singer")
public class SingerController {

    @Autowired
    SingerService singerService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The singer arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Singer>> listAll() {
        return ResponseEntity.ok(this.singerService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Singer> getSinger(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.singerService.getOneById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Singer>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.singerService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.singerService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSinger(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.singerService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<Singer> saveSinger(@RequestBody SingerDTO singerDTO) {
        return ResponseEntity.ok(this.singerService.save(singerDTO));
    }


    @PutMapping()
    public ResponseEntity<Singer> updateSinger(@RequestBody SingerDTO singerDTO) {
        return ResponseEntity.ok(this.singerService.update(singerDTO));
    }
}
