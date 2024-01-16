package carselling.selling.repository;


import carselling.selling.entity.Transmission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;


public interface TransmissionRepository extends CrudRepository<Transmission, String> {

}
