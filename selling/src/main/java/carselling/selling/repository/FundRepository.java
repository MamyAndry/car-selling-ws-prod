package carselling.selling.repository;

import carselling.selling.entity.Fund;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FundRepository extends CrudRepository<Fund, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Fund LIMIT :start,:end")
    List<Fund> paginer(@Param("start") int start, @Param("end") int end);
}
