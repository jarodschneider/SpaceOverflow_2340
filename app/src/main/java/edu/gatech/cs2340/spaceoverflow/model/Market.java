package edu.gatech.cs2340.spaceoverflow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Market {

    private List<TradeGood> tradeGoods;

    public Market(SolarSystem solarSystem) {
        tradeGoods = new ArrayList<>();
        for (TradeGood tradeGood : TradeGood.values()) {
            if (tradeGood.getMTLP() >= solarSystem.getTechLevel().getLevel()) {
                int price = tradeGood.getBasePrice()
                        + tradeGood.getIPL() * (solarSystem.getTechLevel().getLevel() - tradeGood.getMTLP())
                        + (tradeGood.getBasePrice() * (new Random().nextInt(tradeGood.getVar()) / 100));
                tradeGood.setPrice(price);
                tradeGoods.add(tradeGood);
            }
        }
    }

    public List<TradeGood> getTradeGoods() {
        return tradeGoods;
    }
}
