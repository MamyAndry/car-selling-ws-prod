package carselling.selling.repository;


import carselling.selling.entity.ModelGearBox;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ModelGearBoxRepository extends CrudRepository<ModelGearBox, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM Model_Gear_Box LIMIT :start,:end")
    List<ModelGearBox> paginer(@Param("start") int start, @Param("end") int end);
}
