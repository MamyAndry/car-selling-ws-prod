package carselling.selling.repository;


import carselling.selling.entity.Model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ModelRepository extends CrudRepository<Model, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Model LIMIT :start,:end")
    List<Model> paginer(@Param("start") int start, @Param("end") int end);
}
