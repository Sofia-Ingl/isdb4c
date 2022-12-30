package org.example.isdb4c.repository;

import org.example.isdb4c.model.Activity;
import org.example.isdb4c.model.ObservedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Override
    List<Activity> findAll();

    List<Activity> findAllByOrganizations_Id(Integer organizationId);
    List<Activity> findAllByPeople_Id(Integer personId);

}
