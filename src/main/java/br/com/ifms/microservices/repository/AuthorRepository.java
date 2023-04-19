package br.com.ifms.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifms.microservices.model.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Query(value = "SELECT * FROM authors WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<Author> findByName(@Param("name") String name);
}
