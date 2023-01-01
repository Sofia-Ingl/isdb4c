package org.example.isdb4c.repository;

import org.example.isdb4c.model.Activity;
import org.example.isdb4c.model.ObservedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Override
    List<Activity> findAll();

    List<Activity> findAllByOrganizations_Id(Integer organizationId);
    List<Activity> findAllByPeople_Id(Integer personId);

    @Modifying
    @Transactional
    @Query(value = "insert into person_activity(person_id, activity_id) values " +
            "(:personId, :activityId)" , nativeQuery = true)
    void insertPersonActivity(@Param("activityId") Integer activityId,
                                @Param("personId") Integer personId);

    @Modifying
    @Transactional
    @Query(value = "delete from person_activity " +
            "where person_id = :personId and activity_id in :activityIds" , nativeQuery = true)
    void deletePersonActivities(@Param("personId") Integer personId,
                              @Param("activityIds") List<Integer> activityIds);

}
