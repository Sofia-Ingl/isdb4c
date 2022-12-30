package org.example.isdb4c.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employee_position", schema = "public")
public class Position {

    @Id
    @SequenceGenerator(name = "positionSeq", sequenceName = "employee_position_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positionSeq")
    private Integer id;
    @Column(name = "position_name")
    private String name;
    @Column(name = "responsibilities")
    private String responsibilities;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "access_lvl")
    private Integer accessLvl;

}
