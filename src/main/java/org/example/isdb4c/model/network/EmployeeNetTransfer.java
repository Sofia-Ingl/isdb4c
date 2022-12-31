package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Department;
import org.example.isdb4c.model.Employee;
import org.example.isdb4c.model.Position;
import org.example.isdb4c.model.types.EmployeeStatus;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
public class EmployeeNetTransfer implements Serializable {

    private Integer id;
    private String positionName;
    private String departmentName;
    private String name;
    private String passport;
    private String status;

    public EmployeeNetTransfer() {}

    public EmployeeNetTransfer(Employee employee) {
        id = employee.getId();
        positionName = employee.getPosition().getName();
        departmentName = employee.getDepartment().getName();
        name = employee.getName();
        passport = employee.getPassport();
        status = employee.getStatus().getDescription();
    }
}
