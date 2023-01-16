package org.example.isdb4c.repository;

import org.example.isdb4c.model.ObservedPerson;
import org.example.isdb4c.model.Organization;
import org.example.isdb4c.model.types.CaseCompleteness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    public Organization findByIdAndAccessLvlLessThanEqual(Integer id, Integer accessLvl);
    public List<Organization> findAllByAccessLvlLessThanEqual(Integer accessLvl);
    public List<Organization> findAllByCases_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);
    public List<Organization> findAllByMemberships_PersonIdAndAccessLvlLessThanEqual(Integer personId, Integer accessLvl);

    public List<Organization> findAllByIdNotInAndAccessLvlLessThanEqual(List<Integer> ids, Integer accessLvl);


    @Modifying
    @Transactional
    @Query(value = "update organization set " +
            "organization_name = :orgName, " +
            "address = :address, " +
            "access_lvl = :accessLvl " +
            "where id = :orgId", nativeQuery = true)
    void updateOrganizationById(@Param("orgName") String orgName,
                        @Param("address") String address,
                        @Param("accessLvl") Integer accessLvl,
                        @Param("orgId") int orgId);

//    @Modifying
//    @Transactional
//    @Query(value = "insert into organization(organization_name, address, access_lvl)" +
//            " values (:organizationName, :address, :accessLvl)",
//            nativeQuery = true)
//    void addOrganization(@Param("organizationName") String organizationName,
//                   @Param("address") String address,
//                   @Param("accessLvl") Integer accessLvl);
}
