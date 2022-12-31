package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Membership;

@Data
public class MembershipNetTransfer {

    private Integer organizationId;
    private Integer personId;
    private String organizationName;
    private String personName;
    private String memberRole;

    public MembershipNetTransfer() {}

    public MembershipNetTransfer(Membership membership) {
        organizationId = membership.getOrganizationId();
        personId = membership.getPersonId();
        organizationName = membership.getOrganization().getName();
        personName = membership.getPerson().getName();
        memberRole = membership.getMemberRole();
    }

}
