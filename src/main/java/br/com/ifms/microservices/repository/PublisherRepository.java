package br.com.ifms.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifms.microservices.model.Publisher;


public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    

    @Query(value = "SELECT * FROM publishers WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<Publisher> findByName(@Param("name") String name);
}
