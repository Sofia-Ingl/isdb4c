package org.example.isdb4c.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "department", schema = "public")
public class Department {

    @Id
    @SequenceGenerator(name = "departmentSeq", sequenceName = "department_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentSeq")
    private Integer id;

    @Column(name = "abbrev")
    private String abbrev;
    @Column(name = "department_name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy="department")
    private List<Article> articles;

    @OneToMany(mappedBy="department")
    private List<Employee> employees;
}
