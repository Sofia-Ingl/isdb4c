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

    public static EmployeeStatus valueOfDescription(String description) {
        for (EmployeeStatus c : values()) {
            if (c.description.equals(description)) {
                return c;
            }
        }
        return null;
    }
}
