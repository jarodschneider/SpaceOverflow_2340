package edu.gatech.cs2340.spaceoverflow.model;

public enum TechLevel {
    PRE_AGRICULTURE(0),
    AGRICULTURE(1),
    MEDIEVAL(2),
    RENAISSANCE(3),
    EARLY_INDUSTRIAL(4),
    INDUSTRIAL(5),
    POST_INDUSTRIAL(6),
    HI_TECH(7);

    private final int level;

    TechLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
