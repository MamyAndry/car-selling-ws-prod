package carselling.selling.repository;

import carselling.selling.entity.FuelType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Query;


public interface FuelTypeRepository extends CrudRepository<FuelType, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Fuel_type LIMIT :start,:end")
    List<FuelType> paginer(@Param("start") int start, @Param("end") int end);
}
