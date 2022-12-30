package org.example.isdb4c.repository;

import org.example.isdb4c.model.ObservedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<ObservedPerson, Integer> {

    public List<ObservedPerson> findAllByAccessLvlLessThanEqual(Integer accessLvl);
    public List<ObservedPerson> findAllByCases_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);
    public List<ObservedPerson> findAllByWitnessCases_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);

    public List<ObservedPerson> findAllByMemberships_OrganizationIdAndAccessLvlLessThanEqual(Integer organizationId, Integer accessLvl);

}
