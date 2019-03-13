package edu.gatech.cs2340.spaceoverflow.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    public static final Ship GNAT = new Ship("Gnat", 10);

    private List<TradeGood> cargoHold;
    private int capacity;
    private String name;

    private Ship(String name, int capacity) {
        cargoHold = new ArrayList<>();
        this.capacity = capacity;
        this.name = name;
    }

    public Ship(Ship ship) {
        this(ship.name, ship.capacity);
    }

    public List<TradeGood> getCargoHold() {
        return cargoHold;
    }

    public void setCargoHold(List<TradeGood> cargoHold) {
        this.cargoHold = cargoHold;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addGood(TradeGood tradeGood) {
        if (cargoHold.contains(tradeGood)) {
            TradeGood curr = cargoHold.get(cargoHold.indexOf(tradeGood));
            curr.setQuantity(curr.getQuantity() + 1);
        } else {
            TradeGood newGood = new TradeGood(tradeGood);
            newGood.setQuantity(1);
            cargoHold.add(newGood);
        }
        capacity--;
    }

    public String getName() {
        return name;
    }
}
