package carselling.selling.repository;


import carselling.selling.entity.Motorisation;
import org.springframework.data.repository.CrudRepository;


public interface MotorisationRepository extends CrudRepository<Motorisation, String> {
    
}
