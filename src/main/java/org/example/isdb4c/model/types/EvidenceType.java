package org.example.isdb4c.model.types;

public enum EvidenceType {

    SUSPECT_TESTIMONY("показания подозреваемого"),
    ACCUSED_TESTIMONY("показания обвиняемого"),
    VICTIM_TESTIMONY("показания потерпевшего"),
    WITNESS_TESTIMONY("показания свидетеля"),
    EXPERT_OPINION("заключение эксперта"),
    PHYSICAL_EVIDENCE("вещественные доказательства"),
    INVESTIGATIVE_PROTOCOL("протоколы следственных действий"),
    COURT_RECORD("протоколы судебных заседаний"),
    OTHER("иные документы");

    private final String description;

    private EvidenceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static EvidenceType valueOfDescription(String description) {
        for (EvidenceType c : values()) {
            if (c.description.equals(description)) {
                return c;
            }
        }
        return null;
    }
}
