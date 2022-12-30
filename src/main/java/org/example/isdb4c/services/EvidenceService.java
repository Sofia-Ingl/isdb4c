package org.example.isdb4c.services;

import org.example.isdb4c.model.Case;
import org.example.isdb4c.model.Evidence;
import org.example.isdb4c.repository.CaseRepository;
import org.example.isdb4c.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceService {

    private final EvidenceRepository evidenceRepository;

    public EvidenceService(@Autowired EvidenceRepository repository) {
        this.evidenceRepository = repository;
    }

    public List<Evidence> getAllEvidences(Integer accessLvl) {
        return this.evidenceRepository.findAllByAccessLvlLessThanEqual(accessLvl);
    }

    public List<Evidence> getAllCaseEvidences(Integer caseId, Integer accessLvl) {
        return this.evidenceRepository.findAllByCriminalCase_IdAndAccessLvlLessThanEqual(caseId, accessLvl);
    }

}
