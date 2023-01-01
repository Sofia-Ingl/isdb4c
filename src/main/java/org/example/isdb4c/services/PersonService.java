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

//    public void addPerson(PersonNetTransfer newPerson) {
//        this.personRepository.addPerson(
//                newPerson.getName(),
//                PersonStatus.valueOfDescription(newPerson.getStatus()),
//                newPerson.getAlias(),
//                Sex.valueOfDescription(newPerson.getSex()),
//                newPerson.getCitizenship(),
//                newPerson.getPassport(),
//                newPerson.getAddress(),
//                newPerson.getBirthDate(),
//                newPerson.getLocation(),
//                newPerson.getAccessLvl()
//
//        );
//    }

    public void addPerson(PersonNetTransfer newPerson) {

        ObservedPerson p = new ObservedPerson();
        p.setName(newPerson.getName());
        p.setStatus(PersonStatus.valueOfDescription(newPerson.getStatus()));
        p.setAlias(newPerson.getAlias());
        p.setSex(Sex.valueOfDescription(newPerson.getSex()));
        p.setCitizenship(newPerson.getCitizenship());
        p.setPassport(newPerson.getPassport());
        p.setAddress(newPerson.getAddress());
        p.setBirthDate(newPerson.getBirthDate());
        p.setLocation(newPerson.getLocation());
        p.setAccessLvl(newPerson.getAccessLvl());

        this.personRepository.save(p);
    }
}
