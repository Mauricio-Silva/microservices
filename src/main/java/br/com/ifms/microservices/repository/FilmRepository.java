package br.com.ifms.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifms.microservices.model.Film;


public interface FilmRepository extends JpaRepository<Film, Long> {
    

    @Query(value = "SELECT * FROM films WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<Film> findByName(@Param("name") String name);
}
