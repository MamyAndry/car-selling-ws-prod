package carselling.selling.repository;


import carselling.selling.entity.Favorite;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends CrudRepository<Favorite, String> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM Favorite LIMIT :start,:end")
    List<Favorite> paginer(@Param("start") int start, @Param("end") int end);




}
