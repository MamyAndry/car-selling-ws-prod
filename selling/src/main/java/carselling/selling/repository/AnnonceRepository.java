package carselling.selling.repository;


import carselling.selling.entity.Annonce;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Query;


public interface AnnonceRepository extends CrudRepository<Annonce, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM annonce LIMIT :start,:end")
    List<Annonce> paginer(@Param("start") int start, @Param("end") int end);
}
