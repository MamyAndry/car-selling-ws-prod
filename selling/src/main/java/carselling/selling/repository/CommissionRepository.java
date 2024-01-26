package carselling.selling.repository;


import carselling.selling.entity.Commission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Query;


public interface CommissionRepository extends CrudRepository<Commission, String> {
    @Query(nativeQuery = true, value = "SELECT percentage FROM commission c WHERE boundary_inferior <= :price and boundary_superior > :price")
    public Double getCommissionPercentage(Double price);

    @Query(nativeQuery = true, value = "SELECT * FROM Commission LIMIT :start,:end")
    List<Commission> paginer(@Param("start") int start, @Param("end") int end);
}
