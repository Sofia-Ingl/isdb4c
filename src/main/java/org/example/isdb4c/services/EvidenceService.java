package org.example.isdb4c.services;

import org.example.isdb4c.model.Case;
import org.example.isdb4c.model.Evidence;
import org.example.isdb4c.model.network.EvidenceNetTransfer;
import org.example.isdb4c.model.types.EvidenceType;
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

    public void addEvidence(EvidenceNetTransfer newEvidence) {
        Evidence e = new Evidence();
        e.setCaseId(newEvidence.getCaseId());
        e.setType(EvidenceType.valueOfDescription(newEvidence.getType()));
        e.setDescription(newEvidence.getDescription());
        e.setStorage(newEvidence.getStorage());
        e.setAccessLvl(newEvidence.getAccessLvl());
        this.evidenceRepository.save(e);
    }

    public void updateEvidenceById(EvidenceNetTransfer updEvidence, Integer id) {
        this.evidenceRepository.updateEvidenceById(
                EvidenceType.valueOfDescription(updEvidence.getType()),
                updEvidence.getStorage(),
                updEvidence.getDescription(),
                updEvidence.getAccessLvl(),
                id
        );
    }

    public void deleteEvidencesByIds(List<Integer> evidenceIds) {
        this.evidenceRepository.deleteAllById(evidenceIds);
    }

}
