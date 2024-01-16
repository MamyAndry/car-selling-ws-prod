package carselling.selling.repository;


import carselling.selling.entity.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;


public interface PhotoRepository extends CrudRepository<Photo, String> {

}
