package carselling.selling.repository;


import carselling.selling.entity.Brand;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface BrandRepository extends CrudRepository<Brand, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Brand LIMIT :start,:end")
    List<Brand> paginer(@Param("start") int start, @Param("end") int end);
}
