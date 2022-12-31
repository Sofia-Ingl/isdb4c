package org.example.isdb4c.services;

import org.example.isdb4c.model.Membership;
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

    public List<Membership> getAllPersonMemberships(Integer personId) {
        return this.membershipRepository.findAllByPersonId(personId);
    }
}
