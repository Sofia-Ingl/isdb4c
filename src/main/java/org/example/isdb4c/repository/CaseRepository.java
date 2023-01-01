package org.example.isdb4c.repository;

import org.example.isdb4c.model.Article;
import org.example.isdb4c.model.Case;
import org.example.isdb4c.model.Evidence;
import org.example.isdb4c.model.network.CaseNetTransfer;
import org.example.isdb4c.model.types.CaseCompleteness;
import org.example.isdb4c.model.types.PersonStatus;
import org.example.isdb4c.model.types.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<Case, Integer> {

    List<Case> findAllByWitnesses_IdAndAccessLvlLessThanEqual(Integer witnessId, Integer accessLvl);
    List<Case> findAllByPeople_IdAndAccessLvlLessThanEqual(Integer personId, Integer accessLvl);
    List<Case> findAllByOrganizations_IdAndAccessLvlLessThanEqual(Integer organizationId, Integer accessLvl);
    List<Case> findAllByResponsibleEmployees_IdAndAccessLvlLessThanEqual(Integer employeeId, Integer accessLvl);
    List<Case> findAllByIncidents_IdAndAccessLvlLessThanEqual(Integer incidentId, Integer accessLvl);

    List<Case> findAllByAccessLvlLessThanEqual(Integer accessLvl);


    @Modifying
    @Transactional
    @Query(value = "update criminal_case set " +
            "case_name = :caseName, " +
            "description = :caseDescription, " +
            "criminal_case_status = :#{#completeness.toString()} " +
            "where id = :caseId", nativeQuery = true)
    void updateCaseById(@Param("caseName") String caseName,
                        @Param("caseDescription") String caseDescription,
                        @Param("completeness") CaseCompleteness completeness,
                        @Param("caseId") int caseId);


//    @Modifying
//    @Transactional
//    @Query(value = "insert into criminal_case(case_name, description, " +
//            "criminal_case_status, access_lvl) values " +
//            "(:caseName, :caseDescription, " +
//            ":#{#completeness.toString()}, :accessLvl)",
//            nativeQuery = true)
//    void addCase(@Param("caseName") String caseName,
//                   @Param("caseDescription") String caseDescription,
//                   @Param("completeness") CaseCompleteness completeness,
//                   @Param("accessLvl") Integer accessLvl);




    @Modifying
    @Transactional
    @Query(value = "insert into person_case_participant(person_id, case_id) values " +
            "(:personId, :caseId)" , nativeQuery = true)
    void insertCaseParticipants(@Param("caseId") Integer caseId,
                                @Param("personId") Integer personId);

    @Modifying
    @Transactional
    @Query(value = "delete from person_case_participant where " +
            "case_id = :caseId and person_id in :peopleIds" , nativeQuery = true)
    void deleteCaseParticipants(@Param("caseId") Integer caseId,
                                @Param("peopleIds") List<Integer> peopleIds);


    @Modifying
    @Transactional
    @Query(value = "insert into person_case_witness(person_id, case_id) values " +
            "(:personId, :caseId)" , nativeQuery = true)
    void insertCaseWitnesses(@Param("caseId") Integer caseId,
                                @Param("personId") Integer personId);

    @Modifying
    @Transactional
    @Query(value = "delete from person_case_witness where " +
            "case_id = :caseId and person_id in :peopleIds" , nativeQuery = true)
    void deleteCaseWitnesses(@Param("caseId") Integer caseId,
                                @Param("peopleIds") List<Integer> peopleIds);


    @Modifying
    @Transactional
    @Query(value = "insert into organization_case_participant(organization_id, case_id) values " +
            "(:orgId, :caseId)" , nativeQuery = true)
    void insertCaseOrgs(@Param("caseId") Integer caseId,
                             @Param("orgId") Integer orgId);

    @Modifying
    @Transactional
    @Query(value = "delete from organization_case_participant where " +
            "case_id = :caseId and organization_id in :orgIds" , nativeQuery = true)
    void deleteCaseOrgs(@Param("caseId") Integer caseId,
                             @Param("orgIds") List<Integer> orgIds);


    @Modifying
    @Transactional
    @Query(value = "insert into case_related_incident(incident_id, case_id) values " +
            "(:incidentId, :caseId)" , nativeQuery = true)
    void insertCaseIncidents(@Param("caseId") Integer caseId,
                        @Param("incidentId") Integer incidentId);

    @Modifying
    @Transactional
    @Query(value = "delete from case_related_incident where " +
            "case_id = :caseId and incident_id in :incidentIds" , nativeQuery = true)
    void deleteCaseIncidents(@Param("caseId") Integer caseId,
                        @Param("incidentIds") List<Integer> incidentIds);


    @Modifying
    @Transactional
    @Query(value = "insert into court_charges(article_id, case_id) values " +
            "(:articleId, :caseId)" , nativeQuery = true)
    void insertCaseArticles(@Param("caseId") Integer caseId,
                             @Param("articleId") Integer articleId);

    @Modifying
    @Transactional
    @Query(value = "delete from court_charges where " +
            "case_id = :caseId and article_id in :articleIds" , nativeQuery = true)
    void deleteCaseArticles(@Param("caseId") Integer caseId,
                             @Param("articleIds") List<Integer> articleIds);


}
