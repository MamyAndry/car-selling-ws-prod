package carselling.selling.repository;


import carselling.selling.entity.Car;
import org.springframework.data.repository.CrudRepository;


public interface CarRepository extends CrudRepository<Car, String> {
}
