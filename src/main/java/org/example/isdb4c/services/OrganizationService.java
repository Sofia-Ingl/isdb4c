package org.example.isdb4c.services;

import org.example.isdb4c.model.Incident;
import org.example.isdb4c.model.Organization;
import org.example.isdb4c.model.network.OrganizationNetTransfer;
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

    public List<Organization> getAllOrganizationsExcept(List<Integer> ids, Integer accessLvl) {
        return this.organizationRepository.findAllByIdNotInAndAccessLvlLessThanEqual(ids, accessLvl);
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

    public void addOrganization(OrganizationNetTransfer newOrg) {
        Organization o = new Organization();
        o.setAccessLvl(newOrg.getAccessLvl());
        o.setAddress(newOrg.getAddress());
        o.setName(newOrg.getName());
        this.organizationRepository.save(o);
    }

    public void updateOrganization(OrganizationNetTransfer updOrg, Integer id) {
        this.organizationRepository.updateOrganizationById(
                updOrg.getName(),
                updOrg.getAddress(),
                updOrg.getAccessLvl(),
                id
        );
    }
}
