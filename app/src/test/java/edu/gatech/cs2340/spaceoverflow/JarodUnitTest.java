package edu.gatech.cs2340.spaceoverflow;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import edu.gatech.cs2340.spaceoverflow.model.Ship;
import edu.gatech.cs2340.spaceoverflow.model.TradeGood;

import static org.junit.Assert.*;

public class JarodUnitTest {

    private Ship ship;

    @Before
    public void init() {
        ship = new Ship(Ship.GNAT);

        for (TradeGood tradeGood : TradeGood.allGoods) {
            tradeGood.setQuantity(0);
        }
    }

    @Test
    public void nullTest() {
        assertFalse(ship.addGood(null));
    }

    @Test
    public void basicAdd() {
        assertTrue(ship.addGood(TradeGood.FURS));
        assertEquals(9, (int) ship.getCapacity());
    }

    @Test
    public void addSame() {
        assertTrue(ship.addGood(TradeGood.FURS));
        assertTrue(ship.addGood(TradeGood.FURS));
        assertEquals(8, (int) ship.getCapacity());
    }

    @Test
    public void overFillHoldDifferentGoods() {
        int expectedCapacity = 10;
        assertEquals(expectedCapacity, (int) ship.getCapacity());

        for (TradeGood tradeGood : TradeGood.allGoods) {
            assertTrue(ship.addGood(tradeGood));

            //ship remaining capacity decreased with each add
            assertEquals(--expectedCapacity, (int) ship.getCapacity());
            // invariant: ship.capacity >= 0
            assertTrue(ship.getCapacity() >= 0);

            final int INDEX = ship.getCargoHold().indexOf(tradeGood);

            assertEquals(1, (int) ship.getCargoHold().get(INDEX).getQuantity());

            assertTrue(ship.contains(tradeGood));
        }

        for (TradeGood tradeGood : TradeGood.allGoods) {
            assertFalse(ship.addGood(tradeGood));
            assertEquals(expectedCapacity, (int) ship.getCapacity());
            assertEquals(0, (int) ship.getCapacity());

            final int INDEX = ship.getCargoHold().indexOf(tradeGood);
            assertEquals(1, (int) ship.getCargoHold().get(INDEX).getQuantity());
        }
    }

    @Test
    public void overFillSame() {
        int expectedCapacity = 10;
        assertEquals(expectedCapacity, (int) ship.getCapacity());

        TradeGood tradeGood = TradeGood.FURS;
        assertFalse(ship.contains(tradeGood));

        int expectedQuantity = 0;
        final int INDEX = ship.getCargoHold().indexOf(tradeGood);
        assertEquals(expectedQuantity,(int) ship.getCargoHold().get(INDEX).getQuantity());

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (expectedCapacity > 0) {
                assertTrue(ship.addGood(tradeGood));
                assertEquals(++expectedQuantity,(int) ship.getCargoHold().get(INDEX).getQuantity());
                assertEquals(--expectedCapacity, (int) ship.getCapacity());

                assertTrue(ship.contains(tradeGood));
            } else {
                assertFalse(ship.addGood(tradeGood));
                assertEquals(expectedQuantity,(int) ship.getCargoHold().get(INDEX).getQuantity());
                assertEquals(expectedCapacity, (int) ship.getCapacity());
            }
        }
    }

    @Test
    public void addEachToEmptyHoldList() {
        int expectedCapacity = 10;
        for (TradeGood tradeGood : TradeGood.allGoods) {
            ship.setCargoHold(new ArrayList<TradeGood>());
            assertTrue(ship.addGood(tradeGood));
            assertEquals(--expectedCapacity, (int) ship.getCapacity());
        }
    }

    @Test
    public void addEachMultipleToEmptyHoldList() {
        for (TradeGood tradeGood : TradeGood.allGoods) {
            ship.setCargoHold(new ArrayList<TradeGood>());
            ship.setCapacity(10);

            int expectedCapacity = 10;

            for (int i = 0; i < 999999; i++) {
                if (expectedCapacity > 0) {
                    assertTrue(ship.addGood(tradeGood));
                    assertEquals(--expectedCapacity, (int) ship.getCapacity());
                } else {
                    assertFalse(ship.addGood(tradeGood));
                    assertEquals(expectedCapacity, (int) ship.getCapacity());
                }
            }
        }
    }
}
