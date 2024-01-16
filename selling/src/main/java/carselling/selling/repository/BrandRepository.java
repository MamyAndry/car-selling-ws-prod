package carselling.selling.repository;


import carselling.selling.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;


public interface BrandRepository extends CrudRepository<Brand, String> {
    @Query("SELECT nextval('seq_brand')")
    int getNextSequenceValue();
}
