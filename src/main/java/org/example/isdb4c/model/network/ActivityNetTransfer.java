package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Activity;
import org.example.isdb4c.model.types.Legality;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class ActivityNetTransfer implements Serializable {

    private Integer id;
    private String activityType;
    private String legality;
    private String description;

    public ActivityNetTransfer() {
    }

    public ActivityNetTransfer(Activity activity) {
        this.id = activity.getId();
        this.activityType = activity.getActivityType();
        this.description = activity.getDescription();
        this.legality = activity.getLegality().getDescription();
    }
}
