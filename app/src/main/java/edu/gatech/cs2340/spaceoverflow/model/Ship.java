package edu.gatech.cs2340.spaceoverflow.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private List<TradeGood> tradeGoods;

    public Ship() {
        tradeGoods = new ArrayList<>();
    }

    public List<TradeGood> getTradeGoods() {
        return tradeGoods;
    }

    public void setTradeGoods(List<TradeGood> tradeGoods) {
        this.tradeGoods = tradeGoods;
    }
}
