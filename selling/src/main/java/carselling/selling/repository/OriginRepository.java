package carselling.selling.repository;

import carselling.selling.entity.Origin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OriginRepository extends CrudRepository<Origin, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Origin LIMIT :start,:end")
    List<Origin> paginer(@Param("start") int start, @Param("end") int end);
}
