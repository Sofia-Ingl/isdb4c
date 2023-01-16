package org.example.isdb4c.services;

import org.example.isdb4c.model.Membership;
import org.example.isdb4c.model.network.MembershipNetTransfer;
import org.example.isdb4c.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipService(@Autowired MembershipRepository repository) {
        this.membershipRepository = repository;
    }

    public List<Membership> getAllPersonMemberships(Integer personId, Integer accessLvl) {
        return this.membershipRepository.findAllByPersonIdAndOrganization_AccessLvlLessThanEqual(personId, accessLvl);
    }

    public List<Membership> getAllPersonMemberships(Integer personId) {
        return this.membershipRepository.findAllByPersonId(personId);
    }

    public List<Membership> getAllOrganizationMemberships(Integer orgId, Integer accessLvl) {
        return this.membershipRepository.findAllByOrganizationIdAndPerson_AccessLvlLessThanEqual(orgId, accessLvl);
    }

    public void insertMembership(MembershipNetTransfer newMembership) {
        Membership membership = new Membership();
        membership.setPersonId(newMembership.getPersonId());
        membership.setOrganizationId(newMembership.getOrganizationId());
        membership.setMemberRole(newMembership.getMemberRole());
        membershipRepository.save(membership);
    }

    public void insertMemberships(List<MembershipNetTransfer> newMemberships) {
        for (MembershipNetTransfer m:
             newMemberships) {
            insertMembership(m);
        }
    }

    public void deletePersonMemberships(List<Integer> orgIds, Integer personId) {
        membershipRepository.deletePersonMemberships(personId, orgIds);
    }

    public void deleteOrganizationMemberships(List<Integer> peopleIds, Integer orgId) {
        membershipRepository.deleteOrganizationMemberships(orgId, peopleIds);
    }
}
