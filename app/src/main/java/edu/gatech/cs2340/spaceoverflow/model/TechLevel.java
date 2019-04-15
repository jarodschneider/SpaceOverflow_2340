package edu.gatech.cs2340.spaceoverflow.model;

/**
 * TechLevel
 */
public enum TechLevel {
    PRE_AGRICULTURE(0, "Pre-Agriculture"),
    AGRICULTURE(1, "Agriculture"),
    MEDIEVAL(2, "Medieval"),
    RENAISSANCE(3, "Renaissance"),
    EARLY_INDUSTRIAL(4, "Early Industrial"),
    INDUSTRIAL(5, "Industrial"),
    POST_INDUSTRIAL(6, "Post-Industrial"),
    HI_TECH(7, "Hi-Tech");

    private final int level;
    private final String string;

    TechLevel(int level, String string) {
        this.level = level;
        this.string = string;
    }

    /**
     * Gets numeric level
     *
     * @return level
     */
    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return string;
    }
}
