package edu.gatech.cs2340.spaceoverflow.model;

public class Player {
    private String name;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;

    public Player(String name, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill) {
        this.name = name;
        this.pilotSkill = pilotSkill;
        this.fighterSkill = fighterSkill;
        this.traderSkill = traderSkill;
        this.engineerSkill = engineerSkill;
    }

    public String toString() {
        return "Player " + name + " has skill levels: Pilot(" + pilotSkill + "), " +
                "Fighter(" + fighterSkill + "), Trader(" + traderSkill + "), and Engineer(" +
                engineerSkill + ").";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPilotSkill() {
        return pilotSkill;
    }

    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    public Integer getFighterSkill() {
        return fighterSkill;
    }

    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    public Integer getTraderSkill() {
        return traderSkill;
    }

    public void setTraderSkill(int traderSkill) {
        this.traderSkill = traderSkill;
    }

    public Integer getEngineerSkill() {
        return engineerSkill;
    }

    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }
}
