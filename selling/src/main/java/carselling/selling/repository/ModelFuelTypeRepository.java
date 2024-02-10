package carselling.selling.repository;


import carselling.selling.entity.Model;
import carselling.selling.entity.ModelFuelType;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ModelFuelTypeRepository extends CrudRepository<ModelFuelType, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM Model_Fuel_Type LIMIT :start,:end")
    List<ModelFuelType> paginer(@Param("start") int start, @Param("end") int end);
    List<ModelFuelType> findByModel(Model model);
}
