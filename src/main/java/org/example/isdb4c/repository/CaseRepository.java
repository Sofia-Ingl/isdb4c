package org.example.isdb4c.repository;

import org.example.isdb4c.model.Article;
import org.example.isdb4c.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<Case, Integer> {

    List<Case> findAllByWitnesses_IdAndAccessLvlLessThanEqual(Integer witnessId, Integer accessLvl);
    List<Case> findAllByPeople_IdAndAccessLvlLessThanEqual(Integer personId, Integer accessLvl);
    List<Case> findAllByOrganizations_IdAndAccessLvlLessThanEqual(Integer organizationId, Integer accessLvl);
    List<Case> findAllByResponsibleEmployees_IdAndAccessLvlLessThanEqual(Integer employeeId, Integer accessLvl);
    List<Case> findAllByIncidents_IdAndAccessLvlLessThanEqual(Integer incidentId, Integer accessLvl);

    List<Case> findAllByAccessLvlLessThanEqual(Integer accessLvl);
    //List<Case> findAllByIdAndAccessLvlLessThanEqual(Integer id, Integer accessLvl);
}
