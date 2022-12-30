package org.example.isdb4c.services;


import org.example.isdb4c.model.ObservedPerson;
import org.example.isdb4c.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(@Autowired PersonRepository repository) {
        this.personRepository = repository;
    }

    public List<ObservedPerson> getAllObservedPeople(Integer accessLvl) {
        return personRepository.findAllByAccessLvlLessThanEqual(accessLvl);
    }

    public List<ObservedPerson> getAllCaseParticipants(Integer caseId, Integer accessLvl) {
        return personRepository.findAllByCases_IdAndAccessLvlLessThanEqual(caseId, accessLvl);
    }

    public List<ObservedPerson> getAllOrgMembers(Integer orgId, Integer accessLvl) {
        return personRepository.findAllByMemberships_OrganizationIdAndAccessLvlLessThanEqual(orgId, accessLvl);
    }

    public List<ObservedPerson> getAllCaseWitnesses(Integer caseId, Integer accessLvl) {
        return personRepository.findAllByWitnessCases_IdAndAccessLvlLessThanEqual(caseId, accessLvl);
    }
}
