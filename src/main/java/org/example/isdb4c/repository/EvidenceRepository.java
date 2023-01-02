package org.example.isdb4c.repository;

import org.example.isdb4c.model.Article;
import org.example.isdb4c.model.Evidence;
import org.example.isdb4c.model.types.EvidenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface EvidenceRepository extends JpaRepository<Evidence, Integer> {

    List<Evidence> findAllByAccessLvlLessThanEqual(Integer accessLvl);
    List<Evidence> findAllByCriminalCase_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);

    @Modifying
    @Transactional
    @Query(value = "update evidence set " +
            "evidence_type = :#{#evidenceType.toString()}, " +
            "description = :description, " +
            "evidence_storage = :storage, " +
            "access_lvl = :accessLvl " +
            "where id = :evidenceId", nativeQuery = true)
    void updateEvidenceById(@Param("evidenceType")EvidenceType evidenceType,
                            @Param("storage") String storage,
                            @Param("description") String description,
                            @Param("accessLvl") Integer accessLvl,
                            @Param("evidenceId") Integer evidenceId);
}
