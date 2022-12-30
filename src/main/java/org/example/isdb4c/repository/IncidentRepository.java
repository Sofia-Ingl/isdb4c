package org.example.isdb4c.repository;

import org.example.isdb4c.model.Evidence;
import org.example.isdb4c.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {

    List<Incident> findAllByAccessLvlLessThanEqual(Integer accessLvl);
    List<Incident> findAllByCases_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);
}
