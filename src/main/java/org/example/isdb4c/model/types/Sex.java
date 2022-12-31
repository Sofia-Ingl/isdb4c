package org.example.isdb4c.model.types;

public enum Sex {

    MALE("м"),
    FEMALE("ж");

    private final String description;

    private Sex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static Sex valueOfDescription(String description) {
        for (Sex c : values()) {
            if (c.description.equals(description)) {
                return c;
            }
        }
        return null;
    }
}
