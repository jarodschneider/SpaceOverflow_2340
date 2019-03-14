package edu.gatech.cs2340.spaceoverflow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Market {

    private List<TradeGood> tradeGoods;

    public Market(SolarSystem solarSystem) {
        tradeGoods = new ArrayList<>();
        TradeGood[] all = allGoods();

        for (TradeGood tradeGood : all) {
            if (tradeGood.getMTLP() <= solarSystem.getTechLevel().getLevel()) {
                int price = tradeGood.getBasePrice()
                        + tradeGood.getIPL()
                        * (solarSystem.getTechLevel().getLevel() - tradeGood.getMTLP())
                        + (tradeGood.getBasePrice()
                        * (new Random().nextInt(tradeGood.getVar()) / 100));
                tradeGood.setPrice(price);
                tradeGood.setQuantity(new Random().nextInt(15) + 1);
                tradeGoods.add(tradeGood);
            }
        }
    }

    public List<TradeGood> getTradeGoods() {
        return tradeGoods;
    }

    public void buyGood(Ship ship, TradeGood tradeGood) {
        ship.addGood(tradeGood);
    }

    public void sellGood(Ship ship, TradeGood tradeGood) {

    }

    private TradeGood[] allGoods() {
        TradeGood[] all = new TradeGood[TradeGood.allGoods.length];
        for (int i = all.length - 1; i >= 0; i--) {
            TradeGood t = TradeGood.allGoods[i];
            all[i] = new TradeGood(t);
        }

        return all;
    }
}
