package carselling.selling.repository;


import carselling.selling.entity.Vente;
import org.springframework.data.repository.CrudRepository;


public interface VenteRepository extends CrudRepository<Vente, String> {
    
}
