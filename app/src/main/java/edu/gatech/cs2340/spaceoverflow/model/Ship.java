package edu.gatech.cs2340.spaceoverflow.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private List<TradeGood> cargoHold;
    private int capacity;

    public Ship() {
        cargoHold = new ArrayList<>();
        capacity = 10;
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
}
