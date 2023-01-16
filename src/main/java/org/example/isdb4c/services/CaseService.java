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

    public Integer getCaseId(CaseNetTransfer caseWithoutId) {
        return this.caseRepository.findByNameAndDescriptionAndAccessLvl(caseWithoutId.getName(),
                caseWithoutId.getDescription(), caseWithoutId.getAccessLvl()).get().getId();
    }

    public Case getById(Integer id) {
        return this.caseRepository.getById(id);
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

    public void updateCase(CaseNetTransfer updCase, Integer id) {
        this.caseRepository.updateCaseById(
                updCase.getName(),
                updCase.getDescription(),
                CaseCompleteness.valueOfDescription(updCase.getCompleteness()),
                updCase.getAccessLvl(),
                id
        );
    }

//    public void addCase(CaseNetTransfer newCase) {
//        this.caseRepository.addCase(
//                newCase.getName(),
//                newCase.getDescription(),
//                CaseCompleteness.valueOfDescription(newCase.getCompleteness()),
//                newCase.getAccessLvl()
//        );
//    }

    public void addCase(CaseNetTransfer newCase) {
        Case c = new Case();
        c.setName(newCase.getName());
        c.setDescription(newCase.getDescription());
        c.setCompleteness(CaseCompleteness.valueOfDescription(newCase.getCompleteness()));
        c.setAccessLvl(newCase.getAccessLvl());

        this.caseRepository.save(c);
    }

    public void insertParticipants(List<Integer> peopleIds, Integer caseId) {
        for (Integer personId:
                peopleIds) {
            this.caseRepository.insertCaseParticipants(caseId, personId);
        }
    }

    public void deleteParticipants(List<Integer> peopleIds, Integer caseId) {
        this.caseRepository.deleteCaseParticipants(caseId, peopleIds);
    }


    public void insertWitnesses(List<Integer> peopleIds, Integer caseId) {
        for (Integer personId:
                peopleIds) {
            this.caseRepository.insertCaseWitnesses(caseId, personId);
        }
    }

    public void deleteWitnesses(List<Integer> peopleIds, Integer caseId) {
        this.caseRepository.deleteCaseWitnesses(caseId, peopleIds);
    }


    public void insertOrganizations(List<Integer> orgIds, Integer caseId) {
        for (Integer orgId:
                orgIds) {
            this.caseRepository.insertCaseOrgs(caseId, orgId);
        }
    }

    public void deleteOrganizations(List<Integer> orgIds, Integer caseId) {
        this.caseRepository.deleteCaseOrgs(caseId, orgIds);
    }

    public void insertIncidents(List<Integer> incidentIds, Integer caseId) {
        for (Integer incidentId:
                incidentIds) {
            this.caseRepository.insertCaseIncidents(caseId, incidentId);
        }
    }

    public void deleteIncidents(List<Integer> incidentIds, Integer caseId) {
        this.caseRepository.deleteCaseIncidents(caseId, incidentIds);
    }


    public void insertArticles(List<Integer> articleIds, Integer caseId) {
        for (Integer articleId:
                articleIds) {
            this.caseRepository.insertCaseArticles(caseId, articleId);
        }
    }

    public void deleteArticles(List<Integer> articleIds, Integer caseId) {
        this.caseRepository.deleteCaseArticles(caseId, articleIds);
    }

    public void insertResponsibleEmployee(Integer employeeId, Integer caseId) {
        this.caseRepository.insertCaseResponsibleEmployees(caseId, employeeId);

    }
}
