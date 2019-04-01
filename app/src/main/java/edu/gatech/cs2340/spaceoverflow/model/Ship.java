package edu.gatech.cs2340.spaceoverflow.model;

import java.util.Arrays;
import java.util.List;

public class Ship {
    public static final Ship GNAT = new Ship("Gnat", 10, 100);

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

    public Ship(Ship ship) {
        this(ship.name, ship.capacity, ship.fuel);
    }

    public List<TradeGood> getCargoHold() {
        return cargoHold;
    }

    public void setCargoHold(List<TradeGood> cargoHold) {
        this.cargoHold = cargoHold;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean addGood(TradeGood tradeGood) {
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

    public boolean removeGood(TradeGood tradeGood) {
        List<TradeGood> market = Universe.getInstance()
                .getSolarSystems()[Universe.getInstance().getPlayer().getCoords()[0]]
                [Universe.getInstance().getPlayer().getCoords()[1]]
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

    public boolean travelTo(SolarSystem planet) {
        int distance = planet.distanceFrom(Universe.getInstance().getPlayer().getCoords());
        if (distance <= fuel) {
            Universe.getInstance().getPlayer().setCurrentSystem(planet);
            burnFuel(distance);
            return true;
        }
        return false;
    }


    public String getName() {
        return name;
    }

    public void burnFuel(int burned) {
        fuel -= burned;
    }

    public Integer getFuel() {
        return fuel;
    }
}
