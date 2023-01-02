package org.example.isdb4c.controllers;

import org.example.isdb4c.model.network.EvidenceNetTransfer;
import org.example.isdb4c.services.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/evidence")
public class EvidenceController {

    private final EvidenceService evidenceService;

    public EvidenceController(@Autowired EvidenceService evidenceService) {

        this.evidenceService = evidenceService;
    }

    @PostMapping("/{id}/modify")
    public void modifyEvidenceFields(@PathVariable @NotNull Integer id,
                                     @RequestBody EvidenceNetTransfer updEvidence) {
        this.evidenceService.updateEvidenceById(updEvidence, id);
    }
}
