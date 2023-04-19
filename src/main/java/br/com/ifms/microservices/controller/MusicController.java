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

import br.com.ifms.microservices.dto.MusicDTO;
import br.com.ifms.microservices.model.Music;
import br.com.ifms.microservices.service.MusicService;

@RestController
@RequestMapping(value = "/api/music")
public class MusicController {

    @Autowired
    MusicService musicService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The music arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Music>> listAll() {
        return ResponseEntity.ok(this.musicService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusic(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.musicService.getOneById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Music>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.musicService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.musicService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMusic(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.musicService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<Music> saveMusic(@RequestBody MusicDTO musicDTO) {
        return ResponseEntity.ok(this.musicService.save(musicDTO));
    }


    @PutMapping()
    public ResponseEntity<Music> updateMusic(@RequestBody MusicDTO musicDTO) {
        return ResponseEntity.ok(this.musicService.update(musicDTO));
    }
}
