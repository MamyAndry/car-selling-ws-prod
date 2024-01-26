package carselling.selling.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import carselling.selling.entity.User;

public interface UserRepository extends CrudRepository<User, String>{
    @Query(nativeQuery = true, value = "select * from users where email = :email")
    User getUsersByEmail(@Param(value = "email") String email);
}
