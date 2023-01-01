package org.example.isdb4c.services;


import org.example.isdb4c.model.ObservedPerson;
import org.example.isdb4c.model.network.PersonNetTransfer;
import org.example.isdb4c.model.types.PersonStatus;
import org.example.isdb4c.model.types.Sex;
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

    public void updatePerson(PersonNetTransfer updPerson, Integer id) {
        this.personRepository.updatePersonById(
                updPerson.getName(),
                PersonStatus.valueOfDescription(updPerson.getStatus()),
                updPerson.getAlias(),
                Sex.valueOfDescription(updPerson.getSex()),
                updPerson.getCitizenship(),
                updPerson.getPassport(),
                updPerson.getAddress(),
                updPerson.getBirthDate(),
                updPerson.getLocation(),
                id

        );
    }
}
