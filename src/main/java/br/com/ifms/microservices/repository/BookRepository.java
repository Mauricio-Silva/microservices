package br.com.ifms.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifms.microservices.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
    

    @Query(value = "SELECT * FROM books WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<Book> findByName(@Param("name") String name);
}
