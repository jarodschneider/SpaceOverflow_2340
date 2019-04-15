package edu.gatech.cs2340.spaceoverflow.model;

import java.util.List;

/**
 * SolarSystem class
 */
public class SolarSystem {

    private String name;
    private List<Integer> coords;
    private TechLevel techLevel;
    private ResourceLevel resourceLevel;

    private Market market;

    @Override
    public String toString() {
        return "SolarSystem{" +
                "name='" + name + '\'' +
                ", coords=" + coords.toString() +
                ", techLevel=" + techLevel +
                ", resourceLevel=" + resourceLevel +
                '}';
    }

    /**
     * Constructor
     *
     * @param name name
     * @param coords coordinates
     * @param techLevel TechLevel
     * @param resourceLevel ResourceLevel
     */
    public SolarSystem(String name, List<Integer> coords, TechLevel techLevel, ResourceLevel resourceLevel) {
        this.name = name;
        this.coords = coords;
        this.techLevel = techLevel;
        this.resourceLevel = resourceLevel;
        market = null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SolarSystem)) {
            return false;
        }
        SolarSystem other = (SolarSystem) o;
        return other.getName().equals(this.name);
    }

    /**
     * Gets name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets coordinates
     *
     * @return coordinates
     */
    public List<Integer> getCoords() {
        return coords;
    }

    /**
     * Gets TechLevel
     *
     * @return techLevel
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * Gets ResourceLevel
     *
     * @return resourceLevel
     */
    public ResourceLevel getResourceLevel() {
        return resourceLevel;
    }

    /**
     * Initializes market
     */
    public void initializeMarket() {
        market = new Market(this);
    }

    /**
     * Calculates distance from coordinates
     *
     * @param coords coordinates of other location
     * @return distance
     */
    public Integer distanceFrom(List<Integer> coords) {
        return (int) Math.sqrt(
                Math.pow(coords.get(0) - this.coords.get(0), 2) + Math.pow(coords.get(1) - this.coords.get(1), 2));
    }

    /**
     * Gets market
     *
     * @return market
     */
    public Market getMarket() {
        return market;
    }
}