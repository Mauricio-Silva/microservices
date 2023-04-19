package br.com.ifms.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifms.microservices.model.Director;


public interface DirectorRepository extends JpaRepository<Director, Long> {
    

    @Query(value = "SELECT * FROM directors WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<Director> findByName(@Param("name") String name);
}
