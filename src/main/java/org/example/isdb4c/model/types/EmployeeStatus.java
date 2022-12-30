package org.example.isdb4c.model.types;

public enum EmployeeStatus {

    ACTUAL("актуальный"),
    FORMER("бывший");

    private final String description;

    private EmployeeStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
