package edu.gatech.cs2340.spaceoverflow.model;

import java.util.Arrays;

public class SolarSystem {

    private String name;
    private int[] coords;
    private TechLevel techLevel;
    private ResourceLevel resourceLevel;

    private Market market;

    @Override
    public String toString() {
        return "SolarSystem{" +
                "name='" + name + '\'' +
                ", coords=" + Arrays.toString(coords) +
                ", techLevel=" + techLevel +
                ", resourceLevel=" + resourceLevel +
                '}';
    }

    public SolarSystem(String name, int[] coords, TechLevel techLevel, ResourceLevel resourceLevel) {
        this.name = name;
        this.coords = coords;
        this.techLevel = techLevel;
        this.resourceLevel = resourceLevel;
        market = null;
    }

    public String getName() {
        return name;
    }

    public int[] getCoords() {
        return coords;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public ResourceLevel getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(ResourceLevel resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public void initializeMarket() {
        market = new Market(this);
    }

    public Market getMarket() {
        return market;
    }
}