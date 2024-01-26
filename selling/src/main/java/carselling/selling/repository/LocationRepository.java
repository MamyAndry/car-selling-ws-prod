package carselling.selling.repository;


import carselling.selling.entity.Location;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends CrudRepository<Location, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM Location LIMIT :start,:end")
    List<Location> paginer(@Param("start") int start, @Param("end") int end);





}
