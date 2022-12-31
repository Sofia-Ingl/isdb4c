package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Case;
import org.example.isdb4c.model.types.CaseCompleteness;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class CaseNetTransfer implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private String completeness;
    private Integer accessLvl;

    public CaseNetTransfer() {}

    public CaseNetTransfer(Case criminalCase) {
        id = criminalCase.getId();
        name = criminalCase.getName();
        description = criminalCase.getDescription();
        completeness = criminalCase.getCompleteness().getDescription();
        accessLvl = criminalCase.getAccessLvl();
    }
}
