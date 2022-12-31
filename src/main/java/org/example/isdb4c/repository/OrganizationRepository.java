package org.example.isdb4c.repository;

import org.example.isdb4c.model.ObservedPerson;
import org.example.isdb4c.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    public Organization findByIdAndAccessLvlLessThanEqual(Integer id, Integer accessLvl);
    public List<Organization> findAllByAccessLvlLessThanEqual(Integer accessLvl);
    public List<Organization> findAllByCases_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);
    public List<Organization> findAllByMemberships_PersonIdAndAccessLvlLessThanEqual(Integer personId, Integer accessLvl);

}
