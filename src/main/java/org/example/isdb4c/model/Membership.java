package org.example.isdb4c.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@IdClass(MembershipPK.class)
@Table(name = "membership", schema = "public")
public class Membership {

    @Id
    @Column(name = "organization_id")
    private Integer organizationId;

    @Id
    @Column(name = "person_id")
    private Integer personId;

    @ManyToOne
    @MapsId("organizationId")
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private ObservedPerson person;

    @Column(name = "member_role")
    private String memberRole;
}
