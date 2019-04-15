package edu.gatech.cs2340.spaceoverflow;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.spaceoverflow.model.Player;
import edu.gatech.cs2340.spaceoverflow.model.Ship;
import edu.gatech.cs2340.spaceoverflow.model.TradeGood;

import static org.junit.Assert.*;


public class MarceloJUnitTest {
    Player tester;

    @Before
    public void init() {
        tester = new Player("Test", 0, 0, 0, 0);
        tester.setShip(Ship.GNAT);

    }

    @Test
    public void sellNullTest() {
        assertFalse(tester.sellGood(null));
    }

    @Test
    public void sellIncorrectTest() {
        tester.setCredits(0);
        assertFalse(tester.sellGood(TradeGood.WATER));
        assertTrue(tester.getCredits() == 0);
    }

    @Test
    public void sellCorrectTest() {
        tester.setCredits(0);
        assertTrue(tester.getShip().addGood(TradeGood.WATER));
        assertTrue(tester.sellGood(TradeGood.WATER));
        assertTrue(tester.getCredits() == 30);
    }

    @Test
    public void sellMultipleCorrectTest() {
        tester.setCredits(0);
        assertTrue(tester.getShip().addGood(TradeGood.WATER));
        assertTrue(tester.getShip().addGood(TradeGood.FURS));
        assertTrue(tester.getShip().addGood(TradeGood.FOOD));
        assertTrue(tester.sellGood(TradeGood.WATER));
        assertTrue(tester.sellGood(TradeGood.FURS));
        assertTrue(tester.sellGood(TradeGood.FOOD));
        assertTrue(tester.getCredits() == 380);
    }

    @Test
    public void sellMultipleIncorrectTest() {
        tester.setCredits(0);
        assertTrue(tester.getShip().addGood(TradeGood.WATER));
        assertTrue(tester.getShip().addGood(TradeGood.FURS));
        assertTrue(tester.getShip().addGood(TradeGood.FOOD));
        assertTrue(tester.sellGood(TradeGood.WATER));
        assertFalse(tester.sellGood(TradeGood.WATER));
        assertFalse(tester.sellGood(TradeGood.WATER));
        assertTrue(tester.getCredits() == 30);
    }

}