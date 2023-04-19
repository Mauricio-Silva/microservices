package br.com.ifms.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifms.microservices.model.Comic;


public interface ComicRepository extends JpaRepository<Comic, Long> {


   @Query(value = "SELECT * FROM mangas WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<Comic> findByName(@Param("name") String name); 
}
