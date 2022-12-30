package org.example.isdb4c.model;

import lombok.Data;
import org.example.isdb4c.model.types.EmployeeStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "employee", schema = "public")
public class Employee {

    @Id
    @SequenceGenerator(name = "employeeSeq", sequenceName = "employee_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSeq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Department department;

    @Column(name = "employee_name")
    private String name;
    @Column(name = "passport")
    private String passport;

    @Column(name = "current_status")
    private EmployeeStatus status;

    @ManyToMany(mappedBy = "responsibleEmployees")
    private List<Case> cases;
}
