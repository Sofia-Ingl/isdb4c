package org.example.isdb4c.repository;

import org.example.isdb4c.model.Incident;
import org.example.isdb4c.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {
}
