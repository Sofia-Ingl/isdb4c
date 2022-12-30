package org.example.isdb4c.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_entity_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column(name = "login")
    private String login;
    @Column(name = "passwd")
    private String password;

}
