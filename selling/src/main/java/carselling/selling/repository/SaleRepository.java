package carselling.selling.repository;


import carselling.selling.entity.Sale;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface SaleRepository extends CrudRepository<Sale, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM sale LIMIT :start,:end")
    List<Sale> paginer(@Param("start") int start, @Param("end") int end);
}
