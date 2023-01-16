package org.example.isdb4c.model;

import lombok.Data;
import org.example.isdb4c.model.types.PersonStatus;
import org.example.isdb4c.model.types.Sex;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "observed_person", schema = "public")
public class ObservedPerson {

    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "observed_person_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "cathegory")
    private PersonStatus status;
    @Column(name = "person_name")
    private String name;
    @Column(name = "person_alias")
    private String alias;
    @Enumerated(EnumType.STRING)
    @Column(name = "person_sex")
    private Sex sex;
    @Column(name = "citizenship")
    private String citizenship;
    @Column(name = "passport")
    private String passport;
    @Column(name = "address")
    private String address;
    @Column(name = "person_location")
    private String location;
    @Column(name = "access_lvl")
    private Integer accessLvl;


    @ManyToMany
    @JoinTable(
            name = "person_activity",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    List<Activity> activities;

    @OneToMany(mappedBy = "person")
    List<Membership> memberships;

    @ManyToMany(mappedBy = "people")
    List<Case> cases;

    @ManyToMany(mappedBy = "witnesses")
    List<Case> witnessCases;

}
