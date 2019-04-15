package edu.gatech.cs2340.spaceoverflow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Market class
 */
public class Market {

    private List<TradeGood> tradeGoods;

    Market(SolarSystem solarSystem) {
        tradeGoods = new ArrayList<>();
        TradeGood[] all = allGoods();

        for (TradeGood tradeGood : all) {
            if (tradeGood.getMTLP() <= solarSystem.getTechLevel().getLevel()) {
                int price = tradeGood.getBasePrice()
                        + (tradeGood.getIPL()
                        * (solarSystem.getTechLevel().getLevel() - tradeGood.getMTLP()))
                        + (int) (tradeGood.getBasePrice()
                        * ((double) new Random().nextInt(tradeGood.getVar()) / 100));
                tradeGood.setPrice(price);
                tradeGood.setQuantity(new Random().nextInt(15) + 1);
                tradeGoods.add(tradeGood);
            }
        }
    }

    /**
     * Returns market's goods list
     * @return goods list
     */
    public List<TradeGood> getTradeGoods() {
        return tradeGoods;
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
