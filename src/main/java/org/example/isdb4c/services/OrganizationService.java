package org.example.isdb4c.services;

import org.example.isdb4c.model.Incident;
import org.example.isdb4c.model.Organization;
import org.example.isdb4c.repository.IncidentRepository;
import org.example.isdb4c.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(@Autowired OrganizationRepository repository) {
        this.organizationRepository = repository;
    }

    public List<Organization> getAllOrganizations(Integer accessLvl) {
        return this.organizationRepository.findAllByAccessLvlLessThanEqual(accessLvl);
    }

    public List<Organization> getAllCaseParticipantOrgs(Integer caseId, Integer accessLvl) {
        return this.organizationRepository.findAllByCases_IdAndAccessLvlLessThanEqual(caseId, accessLvl);
    }

    public List<Organization> getAllPersonOrgs(Integer personId, Integer accessLvl) {
        return this.organizationRepository.findAllByMemberships_PersonIdAndAccessLvlLessThanEqual(personId, accessLvl);
    }

    public Organization getById(Integer id, Integer accessLvl) {
        return this.organizationRepository.findByIdAndAccessLvlLessThanEqual(id, accessLvl);
    }
}