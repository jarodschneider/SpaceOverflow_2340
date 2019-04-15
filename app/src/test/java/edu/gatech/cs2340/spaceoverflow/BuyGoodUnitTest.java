package edu.gatech.cs2340.spaceoverflow;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.spaceoverflow.model.Player;
import edu.gatech.cs2340.spaceoverflow.model.Ship;
import edu.gatech.cs2340.spaceoverflow.model.TradeGood;

import static org.junit.Assert.*;
import java.util.*;

/**
 * Unit test for to test buyGood()
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BuyGoodUnitTest {

	private Ship ship;
	private Player tester;

	@Before
    public void init() {
    	// initial ship capactiy = 10
        ship = new Ship(Ship.GNAT);
        for (TradeGood tradeGood : TradeGood.allGoods) {
            tradeGood.setQuantity(0);
        }

        tester = new Player("Test", 0, 0, 0, 0);
    }

    @Test
    public void buyNullTest() {
        assertFalse(tester.buyGood(null));
    }

    @Test
    public void buyCorrectTest() {
    	List<TradeGood> cargoHold = tester.getShip().getCargoHold();
		TradeGood water = cargoHold.get(cargoHold.indexOf(TradeGood.WATER));
        water.setQuantity(5);
        int initQuantity = water.getQuantity();

    	tester.setCredits(100);
        assertTrue(tester.buyGood(water));

        assertEquals(9, (int) tester.getShip().getCapacity());
        assertTrue(tester.getCredits() == 70);
        assertTrue(water.getQuantity() == (initQuantity + 1));
    }

    @Test
    public void buyMultipleSameTest() {
    	List<TradeGood> cargoHold = tester.getShip().getCargoHold();
		TradeGood water = cargoHold.get(cargoHold.indexOf(TradeGood.WATER));
        water.setQuantity(5);
        int initQuantity = water.getQuantity();

    	tester.setCredits(100);
        assertTrue(tester.buyGood(water));
        assertTrue(tester.buyGood(water));

        assertEquals(8, (int) tester.getShip().getCapacity());
        assertTrue(tester.getCredits() == 40);
        assertTrue(water.getQuantity() == (initQuantity + 2));
    }

	@Test
	public void buyGoodIncorrectTest() {
		List<TradeGood> cargoHold = tester.getShip().getCargoHold();
		TradeGood water = cargoHold.get(cargoHold.indexOf(TradeGood.WATER));
		int initQuantity = water.getQuantity();

    	tester.setCredits(10);
        assertFalse(tester.buyGood(TradeGood.WATER));
        assertEquals(10, (int) tester.getShip().getCapacity());
        assertTrue(tester.getCredits() == 10);
        assertTrue(water.getQuantity() == initQuantity);

        tester.setCredits(0);
        assertFalse(tester.buyGood(TradeGood.WATER));
        assertEquals(10, (int) tester.getShip().getCapacity());
        assertTrue(tester.getCredits() == 0);
        assertTrue(water.getQuantity() == initQuantity);
    }
   
}