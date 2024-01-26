package carselling.selling.repository;


import carselling.selling.entity.Favoris;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FavorisRepository extends CrudRepository<Favoris, String> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM Favoris LIMIT :start,:end")
    List<Favoris> paginer(@Param("start") int start, @Param("end") int end);




}
