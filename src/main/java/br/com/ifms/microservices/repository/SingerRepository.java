package br.com.ifms.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifms.microservices.model.Singer;


public interface SingerRepository extends JpaRepository<Singer, Long> {
    

    @Query(value = "SELECT * FROM actors WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<Singer> findByName(@Param("name") String name);
}
