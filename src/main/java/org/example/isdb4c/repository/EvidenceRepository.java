package org.example.isdb4c.repository;

import org.example.isdb4c.model.Article;
import org.example.isdb4c.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvidenceRepository extends JpaRepository<Evidence, Integer> {

    List<Evidence> findAllByAccessLvlLessThanEqual(Integer accessLvl);
    List<Evidence> findAllByCriminalCase_IdAndAccessLvlLessThanEqual(Integer caseId, Integer accessLvl);
}
