package org.example.isdb4c.model.types;

public enum CaseCompleteness {
    OPEN("открыто"),
    CLOSED("закрыто");

    private final String description;

    private CaseCompleteness(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
