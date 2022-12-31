package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Case;
import org.example.isdb4c.model.Evidence;
import org.example.isdb4c.model.types.EvidenceType;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
public class EvidenceNetTransfer implements Serializable {

    private Integer id;
    private String caseName;
    private String type;
    private String description;
    private String storage;
    private Integer accessLvl;

    public EvidenceNetTransfer() {}

    public EvidenceNetTransfer(Evidence evidence) {
        id = evidence.getId();
        caseName = evidence.getCriminalCase().getName();
        type = evidence.getType().getDescription();
        description = evidence.getDescription();
        storage = evidence.getStorage();
        accessLvl = evidence.getAccessLvl();
    }
}
