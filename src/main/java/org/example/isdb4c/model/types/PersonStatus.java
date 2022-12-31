package org.example.isdb4c.model.types;

public enum PersonStatus {
    IMPRISONED("заключенный"),
    WANTED("в розыске"),
    SUSPECT("подозреваемый"),
    SURROUNDINGS("член окружения"),
    RESPECTABLE("добропорядочный гражданин");

    private final String description;

    private PersonStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static PersonStatus valueOfDescription(String description) {
        for (PersonStatus c : values()) {
            if (c.description.equals(description)) {
                return c;
            }
        }
        return null;
    }
}

//('заключенный', 'в розыске', 'подозреваемый', 'член окружения', 'добропорядочный гражданин');