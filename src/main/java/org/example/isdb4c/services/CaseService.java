package org.example.isdb4c.services;

import org.example.isdb4c.model.Case;
import org.example.isdb4c.model.network.CaseNetTransfer;
import org.example.isdb4c.model.types.CaseCompleteness;
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

    public void updateCase(CaseNetTransfer updCase, int id) {
        this.caseRepository.updateCaseById(
                updCase.getName(),
                updCase.getDescription(),
                CaseCompleteness.valueOfDescription(updCase.getCompleteness()),
                id
        );
    }

    public void insertParticipants(List<Integer> peopleIds, Integer caseId) {
        for (Integer personId:
                peopleIds) {
            this.caseRepository.insertCaseParticipants(caseId, personId);
        }
    }
}
