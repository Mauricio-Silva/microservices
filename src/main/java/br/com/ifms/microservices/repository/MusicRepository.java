package br.com.ifms.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifms.microservices.model.Music;


public interface MusicRepository extends JpaRepository<Music, Long> {
    

    @Query(value = "SELECT * FROM novels WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<Music> findByName(@Param("name") String name);
}
