package carselling.selling.repository;


import carselling.selling.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Query;


public interface CategoryRepository extends CrudRepository<Category, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Category LIMIT :start,:end")
    List<Category> paginer(@Param("start") int start, @Param("end") int end);

}
