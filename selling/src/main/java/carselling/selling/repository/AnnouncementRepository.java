package carselling.selling.repository;


import carselling.selling.entity.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Query;


public interface AnnouncementRepository extends CrudRepository<Announcement, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM Announcement LIMIT :start,:end")
    List<Announcement> paginer(@Param("start") int start, @Param("end") int end);

    @Query(nativeQuery = true, value = "SELECT * FROM Announcement WHERE status = :status")
    List<Announcement> findByStatus(@Param("status") Integer status);
}
