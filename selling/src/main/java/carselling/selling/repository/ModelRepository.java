package carselling.selling.repository;


import carselling.selling.entity.Model;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, String> {

}
