package org.example.isdb4c.model;

import lombok.Data;
import org.example.isdb4c.model.types.CaseCompleteness;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "criminal_case", schema = "public")
public class Case {

    @Id
    @SequenceGenerator(name = "caseSeq", sequenceName = "criminal_case_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caseSeq")
    private Integer id;

    @Column(name = "case_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "criminal_case_status")
    private CaseCompleteness completeness;
    @Column(name = "access_lvl")
    private Integer accessLvl;

    @OneToMany(mappedBy = "criminalCase")
    List<Evidence> evidences;

    @ManyToMany
    @JoinTable(
            name = "case_related_incident",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "incident_id"))
    List<Incident> incidents;

    @ManyToMany
    @JoinTable(
            name = "court_charges",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    List<Article> articles;

    @ManyToMany
    @JoinTable(
            name = "organization_case_participant",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id"))
    List<Organization> organizations;

    @ManyToMany
    @JoinTable(
            name = "person_case_participant",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    List<ObservedPerson> people;

    @ManyToMany
    @JoinTable(
            name = "person_case_witness",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    List<ObservedPerson> witnesses;

    @ManyToMany
    @JoinTable(
            name = "responsible_employee",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    List<Employee> responsibleEmployees;
}
