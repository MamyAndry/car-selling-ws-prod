package carselling.selling.repository;


import carselling.selling.entity.GearBox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;


public interface GearBoxRepository extends CrudRepository<GearBox, String> {
    @Query("SELECT nextval('seq_motorisation')")
    int getNextSequenceValue();

}
