package carselling.selling.repository;

import carselling.selling.entity.GearBox;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GearBoxRepository extends CrudRepository<GearBox, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Gear_box LIMIT :start,:end")
    List<GearBox> paginer(@Param("start") int start, @Param("end") int end);
}
