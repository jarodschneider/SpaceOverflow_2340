package edu.gatech.cs2340.spaceoverflow.model;

import java.util.Arrays;
import java.util.List;

/**
 * Ship class
 */
public class Ship {
    static final Ship GNAT = new Ship("Gnat", 10, 100);

    private List<TradeGood> cargoHold;
    private int capacity;
    private String name;
    private int fuel;

    private Ship(String name, int capacity, int fuel) {
        cargoHold = Arrays.asList(TradeGood.allGoods);
        this.capacity = capacity;
        this.name = name;
        this.fuel = fuel;
    }

    Ship(Ship ship) {
        this(ship.name, ship.capacity, ship.fuel);
    }

    /**
     * Gets cargo hold
     *
     * @return cargoHold
     */
    public List<TradeGood> getCargoHold() {
        return cargoHold;
    }

    /**
     * Gets remaining capacity
     *
     * @return capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets remaining capacity
     *
     * @param capacity new capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    boolean addGood(TradeGood tradeGood) {
        if (capacity > 0) {
            if (cargoHold.contains(tradeGood)) {
                TradeGood curr = cargoHold.get(cargoHold.indexOf(tradeGood));
                curr.setQuantity(curr.getQuantity() + 1);
            } else {
                TradeGood newGood = new TradeGood(tradeGood);
                newGood.setQuantity(1);
                cargoHold.add(newGood);
            }
            capacity--;
            return true;
        } else {
            return false;
        }
    }

    boolean removeGood(TradeGood tradeGood) {
        List<TradeGood> market = Universe.getInstance()
                .getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
                .get(Universe.getInstance().getPlayer().getCoords().get(1))
                .getMarket().getTradeGoods();
        if (market.contains(tradeGood)) {

            TradeGood curr = market.get(market.indexOf(tradeGood));
            curr.setQuantity(curr.getQuantity() + 1);
            cargoHold.get(cargoHold.indexOf(tradeGood))
                    .setQuantity(cargoHold.get(cargoHold.indexOf(tradeGood)).getQuantity() - 1);
        } else {
            return false;
        }
        capacity++;
        return true;
    }

    /**
     * Travels ship to new SolarSystem
     *
     * @param planet new system
     * @return true if successfully traveled
     */
    public boolean travelTo(SolarSystem planet) {
        int distance = planet.distanceFrom(Universe.getInstance().getPlayer().getCoords());
        if (distance <= fuel) {
            Universe.getInstance().getPlayer().setCurrentSystem(planet);
            burnFuel(distance);
            return true;
        }
        return false;
    }


    /**
     * Gets ship's name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    private void burnFuel(int burned) {
        fuel -= burned;
    }

    /**
     * Gets fuel
     *
     * @return fuel
     */
    public Integer getFuel() {
        return fuel;
    }
}
