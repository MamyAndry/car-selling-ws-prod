package carselling.selling.repository;


import carselling.selling.entity.Motorisation;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MotorisationRepository extends CrudRepository<Motorisation, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Motorisation LIMIT :start,:end")
    List<Motorisation> paginer(@Param("start") int start, @Param("end") int end);
}
