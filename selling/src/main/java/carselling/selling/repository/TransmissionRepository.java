package carselling.selling.repository;


import carselling.selling.entity.Transmission;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransmissionRepository extends CrudRepository<Transmission, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Transmission LIMIT :start,:end")
    List<Transmission> paginer(@Param("start") int start, @Param("end") int end);
}
