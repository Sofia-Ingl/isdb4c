package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Position;

import javax.persistence.Column;

@Data
public class PositionNetTransfer {

    private Integer id;
    private String name;
    private String responsibilities;
    private String qualification;
    private Integer accessLvl;

    public PositionNetTransfer() {}

    public PositionNetTransfer(Position position) {
        id = position.getId();
        name = position.getName();
        responsibilities = position.getResponsibilities();
        qualification = position.getQualification();
        accessLvl = position.getAccessLvl();
    }
}
