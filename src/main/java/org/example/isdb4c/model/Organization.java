package org.example.isdb4c.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "organization", schema = "public")
public class Organization {

    @Id
    @SequenceGenerator(name = "organizationSeq", sequenceName = "organization_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizationSeq")
    private Integer id;
    @Column(name = "organization_name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "access_lvl")
    private Integer accessLvl;

    @ManyToMany
    @JoinTable(
            name = "organization_activity",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    List<Activity> activities;

    @OneToMany(mappedBy = "organization")
    List<Membership> memberships;

    @ManyToMany(mappedBy = "organizations")
    List<Case> cases;
}
