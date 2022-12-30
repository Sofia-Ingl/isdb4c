package org.example.isdb4c.model.types;

public enum Legality {
    LEGAL("законная"),
    ILLEGAL("незаконная"),
    REPREHENSIBLE("предосудительная");

    private final String description;

    private Legality(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
