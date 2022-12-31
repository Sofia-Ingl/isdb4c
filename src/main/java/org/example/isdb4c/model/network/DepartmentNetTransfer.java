package org.example.isdb4c.model.network;

import lombok.Data;
import org.example.isdb4c.model.Department;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class DepartmentNetTransfer implements Serializable {

    private Integer id;
    private String abbrev;
    private String name;
    private String description;

    public DepartmentNetTransfer() {}

    public DepartmentNetTransfer(Department department) {
        id = department.getId();
        abbrev = department.getAbbrev();
        name = department.getName();
        description = department.getDescription();
    }
}
