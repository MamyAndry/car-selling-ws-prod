package carselling.selling.repository;


import carselling.selling.entity.Profit;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProfitRepository extends CrudRepository<Profit, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Profit LIMIT :start,:end")
    List<Profit> paginer(@Param("start") int start, @Param("end") int end);
}
