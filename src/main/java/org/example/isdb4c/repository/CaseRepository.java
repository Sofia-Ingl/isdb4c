package org.example.isdb4c.repository;

import org.example.isdb4c.model.Article;
import org.example.isdb4c.model.Case;
import org.example.isdb4c.model.Evidence;
import org.example.isdb4c.model.network.CaseNetTransfer;
import org.example.isdb4c.model.types.CaseCompleteness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.OneToMany;
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

    @Modifying
    @Transactional
    @Query(value = "insert into person_case_participant(person_id, case_id) values " +
            "(:personId, :caseId)" , nativeQuery = true)
    void insertCaseParticipants(@Param("caseId") Integer caseId,
                                @Param("personId") Integer personId);
}
