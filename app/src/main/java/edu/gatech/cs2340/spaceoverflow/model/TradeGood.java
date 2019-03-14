package edu.gatech.cs2340.spaceoverflow.model;


public class TradeGood {
    public static final TradeGood WATER = new TradeGood("Water",
            30, 0, 0, 2, 3, 4,
            ResourceLevel.LOTSOFWATER, ResourceLevel.DESERT);

    public static final TradeGood FURS = new TradeGood("Furs",
            250, 0, 0, 0, 10, 10,
            ResourceLevel.RICHFAUNA, ResourceLevel.LIFELESS);

    public static final TradeGood FOOD = new TradeGood("Food",
            100, 1, 0, 1, 5, 5,
            ResourceLevel.RICHSOIL, ResourceLevel.POORSOIL);

    public static final TradeGood ORE = new TradeGood("Ore",
            350, 2, 2, 3, 20, 10,
            ResourceLevel.MINERALRICH, ResourceLevel.MINERALPOOR);

    public static final TradeGood GAMES = new TradeGood("Games",
            250, 3, 1, 6, -10, 5,
            ResourceLevel.ARTISTIC, null);

    public static final TradeGood FIREARMS = new TradeGood("Firearms",
            1250, 3, 1, 5, -75, 100,
            ResourceLevel.WARLIKE, null);

    public static final TradeGood MEDICINE = new TradeGood("Medicine",
            650, 4, 1, 6, -20, 10,
            ResourceLevel.LOTSOFHERBS, null);

    public static final TradeGood MACHINES = new TradeGood("Machines",
            900, 4, 3, 5, -30, 5,
            null, null);

    public static final TradeGood NARCOTICS = new TradeGood("Narcotics",
            3500, 5, 0, 5, -125, 150,
            ResourceLevel.WEIRDMUSHROOMS, null);

    public static final TradeGood ROBOTS = new TradeGood("Robots",
            5000, 6, 4, 7, -150, 100,
            null, null);

    public static final TradeGood[] allGoods = {WATER, FURS, FOOD, ORE, GAMES, FIREARMS, MEDICINE,
                                                MACHINES, NARCOTICS, ROBOTS};

    private final String name;
    private final int basePrice;
    private final int MTLP;
    private final int MTLU;
    private final int TTP;
    private final int IPL;
    private final int var;
    private final ResourceLevel CR;
    private final ResourceLevel ER;

    private int price;
    private int quantity;

    private TradeGood(String name, int basePrice, int MTLP, int MTLU, int TTP, int IPL, int var,
                      ResourceLevel CR, ResourceLevel ER) {

        this.name = name;
        this.basePrice = basePrice;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.IPL = IPL;
        this.var = var;
        this.CR = CR;
        this.ER = ER;
        price = basePrice;
        quantity = 0;
    }

    public TradeGood(TradeGood tradeGood) {
        name = tradeGood.name;
        basePrice = tradeGood.basePrice;
        MTLP = tradeGood.MTLP;
        MTLU = tradeGood.MTLU;
        TTP = tradeGood.TTP;
        IPL = tradeGood.IPL;
        var = tradeGood.var;
        CR = tradeGood.CR;
        ER = tradeGood.ER;
        price = tradeGood.price;
        quantity = tradeGood.quantity;
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

    public Integer getIPL() {
        return IPL;
    }

    public Integer getVar() {
        return var;
    }

    public Integer getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ResourceLevel getCR() {
        return CR;
    }

    public ResourceLevel getER() {
        return ER;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public String toString() {
        return name + ", price = " + price + ", quantity = " + quantity;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TradeGood)) {
            return false;
        }

        TradeGood t = (TradeGood) o;

        return this.name.equals(t.name);
    }
}
