package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Incident;

import java.time.LocalDateTime;

@Data
public class IncidentNetTransfer {

    private Integer id;
    private LocalDateTime time;
    private String place;
    private String type;
    private String description;
    private Integer accessLvl;

    public IncidentNetTransfer() {}

    public IncidentNetTransfer(Incident incident) {
        id = incident.getId();
        time = incident.getTime();
        place = incident.getPlace();
        type = incident.getType();
        description = incident.getDescription();
        accessLvl = incident.getAccessLvl();
    }
}
