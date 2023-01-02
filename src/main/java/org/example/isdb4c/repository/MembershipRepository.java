package org.example.isdb4c.repository;

import org.example.isdb4c.model.Incident;
import org.example.isdb4c.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    List<Membership> findAllByPersonId(Integer personId);

    List<Membership> findAllByPersonIdAndOrganization_AccessLvlLessThanEqual(Integer personId, Integer accessLvl);

    List<Membership> findAllByOrganizationIdAndPerson_AccessLvlLessThanEqual(Integer orgId, Integer accessLvl);

    @Modifying
    @Transactional
    @Query(value = "delete from membership where " +
            "person_id = :personId and organization_id in :orgIds" , nativeQuery = true)
    void deletePersonMemberships(@Param("personId") Integer personId,
                                @Param("orgIds") List<Integer> orgIds);

    @Modifying
    @Transactional
    @Query(value = "delete from membership where " +
            "organization_id = :orgId and person_id in :peopleIds" , nativeQuery = true)
    void deleteOrganizationMemberships(@Param("orgId") Integer orgId,
                                       @Param("peopleIds") List<Integer> peopleIds);
}
