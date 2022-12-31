package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Organization;

import javax.persistence.Column;

@Data
public class OrganizationNetTransfer {

    private Integer id;
    private String name;
    private String address;
    private Integer accessLvl;

    public OrganizationNetTransfer() {}

    public OrganizationNetTransfer(Organization organization) {
        id = organization.getId();
        name = organization.getName();
        address = organization.getAddress();
        accessLvl = organization.getAccessLvl();
    }
}
