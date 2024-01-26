package carselling.selling.repository;


import carselling.selling.entity.Photo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PhotoRepository extends CrudRepository<Photo, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Photo LIMIT :start,:end")
    List<Photo> paginer(@Param("start") int start, @Param("end") int end);
}
