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

import br.com.ifms.microservices.dto.SeriesDTO;
import br.com.ifms.microservices.model.Series;
import br.com.ifms.microservices.service.SeriesService;

@RestController
@RequestMapping(value = "/api/series")
public class SeriesController {

    @Autowired
    SeriesService seriesService;


    @GetMapping()
    public ResponseEntity<String> showMessage() {
        return ResponseEntity.ok("The series arrived");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Series>> listAll() {
        return ResponseEntity.ok(this.seriesService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Series> getSeries(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.seriesService.getOneById(id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Series>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.seriesService.getByName(name));
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok(this.seriesService.getCount());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeries(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.seriesService.delete(id));
    }


    @PostMapping()
    public ResponseEntity<Series> saveSeries(@RequestBody SeriesDTO seriesDTO) {
        return ResponseEntity.ok(this.seriesService.save(seriesDTO));
    }


    @PutMapping()
    public ResponseEntity<Series> updateSeries(@RequestBody SeriesDTO seriesDTO) {
        return ResponseEntity.ok(this.seriesService.update(seriesDTO));
    }
}
