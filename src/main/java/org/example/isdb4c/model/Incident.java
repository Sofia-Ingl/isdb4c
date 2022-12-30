package org.example.isdb4c.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "incident", schema = "public")
public class Incident {

    @Id
    @SequenceGenerator(name = "incidentSeq", sequenceName = "evidence_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evidenceSeq")
    private Integer id;

    @Column(name = "event_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime time;
    @Column(name = "place")
    private String place;
    @Column(name = "event_type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "access_lvl")
    private Integer accessLvl;

    @ManyToMany(mappedBy = "incidents")
    List<Case> cases;
}
