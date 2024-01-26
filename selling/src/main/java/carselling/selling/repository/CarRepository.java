package carselling.selling.repository;


import carselling.selling.entity.Car;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CarRepository extends CrudRepository<Car, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Car LIMIT :start,:end")
    List<Car> paginer(@Param("start") int start, @Param("end") int end);
}
