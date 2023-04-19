package br.com.ifms.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ifms.microservices.model.RecordCompany;


public interface RecordCompanyRepository extends JpaRepository<RecordCompany, Long> {
    

    @Query(value = "SELECT * FROM webtoons WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<RecordCompany> findByName(@Param("name") String name);
}
