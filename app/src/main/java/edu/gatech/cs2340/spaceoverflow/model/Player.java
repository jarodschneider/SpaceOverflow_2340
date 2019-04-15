package edu.gatech.cs2340.spaceoverflow.model;

import java.util.List;

/**
 * Player class
 */
public class Player {

    private String name;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;

    private int credits;
    private List<Integer> coords;
    private SolarSystem currentSystem;
    private Ship ship;

    /**
     * Player constructor
     *
     * @param name name
     * @param pilotSkill pilot skills
     * @param fighterSkill fighter skills
     * @param traderSkill trader skills
     * @param engineerSkill engineer skills
     */
    public Player(String name, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill) {

        this.name = name;
        this.pilotSkill = pilotSkill;
        this.fighterSkill = fighterSkill;
        this.traderSkill = traderSkill;
        this.engineerSkill = engineerSkill;
        credits = 1000;

        ship = new Ship(Ship.GNAT);
    }

    /**
     * Buys goods
     *
     * @param tradeGood tradeGood to buy
     * @return true if successfully bought
     */
    public boolean buyGood(TradeGood tradeGood) {
        if ((tradeGood.getPrice() <= credits) && (tradeGood.getQuantity() > 0)) {
            if (ship.addGood(tradeGood)) {
                credits -= tradeGood.getPrice();
                tradeGood.setQuantity(tradeGood.getQuantity() - 1);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Sells goods
     *
     * @param tradeGood good to sell
     * @return true if successfully sold
     */
    public boolean sellGood(TradeGood tradeGood) {
        if (ship.getCargoHold().get(ship.getCargoHold().indexOf(tradeGood)).getQuantity() > 0) {
            if (ship.removeGood(tradeGood)) {
                credits += tradeGood.getPrice();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Player " + name + " has skill levels: Pilot(" + pilotSkill + "), " +
                "Fighter(" + fighterSkill + "), Trader(" + traderSkill + "), and Engineer(" +
                engineerSkill + ").";
    }

    void setCurrentSystem(SolarSystem newSystem) {
        currentSystem = newSystem;
        coords = currentSystem.getCoords();
        currentSystem.initializeMarket();
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
     * Sets name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets pilot skills
     *
     * @return skill points
     */
    public Integer getPilotSkill() {
        return pilotSkill;
    }

    /**
     * Sets pilot skills
     *
     * @param pilotSkill new skills
     */
    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    /**
     * Gets fighter skills
     * @return skill points
     */
    public Integer getFighterSkill() {
        return fighterSkill;
    }

    /**
     * Sets fighter skills
     *
     * @param fighterSkill skill points
     */
    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    /**
     * Gets trader skills
     *
     * @return skill points
     */
    public Integer getTraderSkill() {
        return traderSkill;
    }

    /**
     * Sets trader skills
     *
     * @param traderSkill skill points
     */
    public void setTraderSkill(int traderSkill) {
        this.traderSkill = traderSkill;
    }

    /**
     * Gets engineer skills
     *
     * @return points
     */
    public Integer getEngineerSkill() {
        return engineerSkill;
    }

    /**
     * Sets engineer skills
     *
     * @param engineerSkill points
     */
    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    /**
     * Gets player's ship
     *
     * @return ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Gets player coordinates
     *
     * @return coordinates
     */
    public List<Integer> getCoords() {
        return coords;
    }

    /**
     * Sets player coordinates
     *
     * @param coords coordinates
     */
    public void setCoords(List<Integer> coords) {
        this.coords = coords;
    }

    /**
     * Gets player's credits
     *
     * @return credits
     */
    public Integer getCredits() {
        return credits;
    }

    /**
     * Sets player's credits
     *
     * @param credits new credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
}
