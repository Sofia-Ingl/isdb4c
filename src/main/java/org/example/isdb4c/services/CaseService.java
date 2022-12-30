package org.example.isdb4c.services;

import org.example.isdb4c.model.Case;
import org.example.isdb4c.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {

    private final CaseRepository caseRepository;

    public CaseService(@Autowired CaseRepository repository) {
        this.caseRepository = repository;
    }

    public List<Case> getAllCases(Integer accessLvl) {
        return this.caseRepository.findAllByAccessLvlLessThanEqual(accessLvl);
    }

    public List<Case> getAllPersonCases(Integer personId, Integer accessLvl) {
        return this.caseRepository.findAllByPeople_IdAndAccessLvlLessThanEqual(personId, accessLvl);
    }

    public List<Case> getAllPersonWitnessCases(Integer personId, Integer accessLvl) {
        return this.caseRepository.findAllByWitnesses_IdAndAccessLvlLessThanEqual(personId, accessLvl);
    }

    public List<Case> getAllOrganizationCases(Integer orgId, Integer accessLvl) {
        return this.caseRepository.findAllByOrganizations_IdAndAccessLvlLessThanEqual(orgId, accessLvl);
    }

    public List<Case> getAllEmployeeCases(Integer employeeId, Integer accessLvl) {
        return this.caseRepository.findAllByResponsibleEmployees_IdAndAccessLvlLessThanEqual(employeeId, accessLvl);
    }

    public List<Case> getAllIncidentCases(Integer incidentId, Integer accessLvl) {
        return this.caseRepository.findAllByIncidents_IdAndAccessLvlLessThanEqual(incidentId, accessLvl);
    }
}
