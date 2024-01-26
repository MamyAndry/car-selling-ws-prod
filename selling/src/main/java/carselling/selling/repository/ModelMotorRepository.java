package carselling.selling.repository;


import carselling.selling.entity.ModelMotor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ModelMotorRepository extends CrudRepository<ModelMotor, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM Model_Motor LIMIT :start,:end")
    List<ModelMotor> paginer(@Param("start") int start, @Param("end") int end);
}
