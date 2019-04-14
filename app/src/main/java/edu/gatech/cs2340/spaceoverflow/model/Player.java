package edu.gatech.cs2340.spaceoverflow.model;

import java.util.List;

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

    public Player(String name, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill) {

        this.name = name;
        this.pilotSkill = pilotSkill;
        this.fighterSkill = fighterSkill;
        this.traderSkill = traderSkill;
        this.engineerSkill = engineerSkill;
        credits = 1000;

        ship = new Ship(Ship.GNAT);
    }

    public boolean buyGood(TradeGood tradeGood) {
        if (tradeGood.getPrice() <= credits && tradeGood.getQuantity() > 0) {
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

    public boolean sellGood(TradeGood tradeGood) {
        List<TradeGood> market = Universe.getInstance()
                .getSolarSystems().get(coords.get(0))
                .get(coords.get(1))
                .getMarket().getTradeGoods();
        if (market.contains(tradeGood)) {
            if (ship.removeGood(tradeGood)) {
                TradeGood curr = market.get(market.indexOf(tradeGood));
                curr.setQuantity(curr.getQuantity() + 1);

                credits += tradeGood.getPrice();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "Player " + name + " has skill levels: Pilot(" + pilotSkill + "), " +
                "Fighter(" + fighterSkill + "), Trader(" + traderSkill + "), and Engineer(" +
                engineerSkill + ").";
    }

    public void setCurrentSystem(SolarSystem newSystem) {
        currentSystem = newSystem;
        coords = currentSystem.getCoords();
        currentSystem.initializeMarket();
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

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<Integer> getCoords() {
        return coords;
    }

    public void setCoords(List<Integer> coords) {
        this.coords = coords;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
