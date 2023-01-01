package org.example.isdb4c.model;

import lombok.Data;
import org.example.isdb4c.model.types.EvidenceType;

import javax.persistence.*;

@Entity
@Data
@Table(name = "evidence", schema = "public")
public class Evidence {

    @Id
    @SequenceGenerator(name = "evidenceSeq", sequenceName = "evidence_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evidenceSeq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "case_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Case criminalCase;

    @Column(name = "case_id")
    private Integer caseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "evidence_type")
    private EvidenceType type;
    @Column(name = "description")
    private String description;
    @Column(name = "evidence_storage")
    private String storage;
    @Column(name = "access_lvl")
    private Integer accessLvl;

}
