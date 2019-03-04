package edu.gatech.cs2340.spaceoverflow.model;

public enum TradeGood {
    WATER(30, 0, 0, 2, 3, 4, ResourceLevel.LOTSOFWATER, ResourceLevel.DESERT),
    FURS(250, 0, 0, 0, 10, 10, ResourceLevel.RICHFAUNA, ResourceLevel.LIFELESS),
    FOOD(100, 1, 0, 1, 5, 5, ResourceLevel.RICHSOIL, ResourceLevel.POORSOIL),
    ORE(350, 2, 2, 3, 20, 10, ResourceLevel.MINERALRICH, ResourceLevel.MINERALPOOR),
    GAMES(250, 3, 1, 6, -10, 5, ResourceLevel.ARTISTIC, null),
    FIREARMS(1250, 3, 1, 5, -75, 100, ResourceLevel.WARLIKE, null),
    MEDICINE(650, 4, 1, 6, -20, 10, ResourceLevel.LOTSOFHERBS, null),
    MACHINES(900, 4, 3, 5, -30, 5, null, null),
    NARCOTICS(3500, 5, 0, 5, -125, 150, ResourceLevel.WEIRDMUSHROOMS, null),
    ROBOTS(5000, 6, 4, 7, -150, 100, null, null);

    private final int basePrice;
    private final int MTLP;
    private final int MTLU;
    private final int TTP;
    private final int IPL;
    private final int var;
    private final ResourceLevel CR;
    private final ResourceLevel ER;

    private int price;

    TradeGood(int basePrice, int MTLP, int MTLU, int TTP, int IPL, int var, ResourceLevel CR, ResourceLevel ER) {
        this.basePrice = basePrice;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.IPL = IPL;
        this.var = var;
        this.CR = CR;
        this.ER = ER;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMTLP() {
        return MTLP;
    }

    public int getMTLU() {
        return MTLU;
    }

    public int getTTP() {
        return TTP;
    }

    public int getIPL() {
        return IPL;
    }

    public int getVar() {
        return var;
    }

    public ResourceLevel getCR() {
        return CR;
    }

    public ResourceLevel getER() {
        return ER;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
