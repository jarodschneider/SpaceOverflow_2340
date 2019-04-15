package edu.gatech.cs2340.spaceoverflow.model;

/**
 * Resource Level
 */
public enum ResourceLevel {
    NOSPECIALRESOURCES(0, "NOSPECIALRESOURCES"),
    MINERALRICH(1, "MINERALRICH"),
    MINERALPOOR(2, "MINERALPOOR"),
    DESERT(3, "DESERT"),
    LOTSOFWATER(4, "LOTSOFWATER"),
    RICHSOIL(5, "RICHSOIL"),
    POORSOIL(6, "POORSOIL"),
    RICHFAUNA(7, "RICHFAUNA"),
    LIFELESS(8, "LIFELESS"),
    WEIRDMUSHROOMS(9, "WEIRDMUSHROOMS"),
    LOTSOFHERBS(10, "LOTSOFHERBS"),
    ARTISTIC(11, "ARTISTIC"),
    WARLIKE(12, "WARLIKE");

    private final int resourceLevel;
    private final String string;

    ResourceLevel(int resourceLevel, String string) {
        this.resourceLevel = resourceLevel;
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
