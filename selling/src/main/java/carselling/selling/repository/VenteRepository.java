package carselling.selling.repository;


import carselling.selling.entity.Vente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface VenteRepository extends CrudRepository<Vente, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Vente LIMIT :start,:end")
    List<Vente> paginer(@Param("start") int start, @Param("end") int end);
}
