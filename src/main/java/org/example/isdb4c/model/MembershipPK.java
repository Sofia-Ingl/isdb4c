package org.example.isdb4c.model;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class MembershipPK implements Serializable {

    @Column(name = "organization_id")
    private Integer organizationId;
    @Column(name = "person_id")
    private Integer personId;
}
