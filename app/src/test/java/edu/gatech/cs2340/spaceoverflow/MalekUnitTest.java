package edu.gatech.cs2340.spaceoverflow;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.spaceoverflow.model.Ship;
import edu.gatech.cs2340.spaceoverflow.model.TradeGood;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MalekUnitTest {



    private Ship ship;

    @Before
    public void init() {
        ship = new Ship(Ship.GNAT);

        for (TradeGood tradeGood : TradeGood.allGoods) {
            tradeGood.setQuantity(5);
        }
    }

    @Test
    public void nullTest() {
        assertFalse(ship.removeGood(null));
    }

    @Test
    public void basicRemove() {
        assertTrue(ship.removeGood(TradeGood.FURS));
        assertEquals(11, (int) ship.getCapacity());

    }

    @Test
    public void removeSame() {
        assertTrue(ship.removeGood(TradeGood.FURS));
        assertTrue(ship.removeGood(TradeGood.FURS));
        assertEquals(12, (int) ship.getCapacity());
    }

    @Test
    public void removeNonExistant() {
        assertTrue(ship.removeGood(TradeGood.FURS));
        assertTrue(ship.removeGood(TradeGood.FURS));
        assertTrue(ship.removeGood(TradeGood.FURS));
        assertTrue(ship.removeGood(TradeGood.FURS));
        assertTrue(ship.removeGood(TradeGood.FURS));
        assertEquals(15, (int) ship.getCapacity());
        assertFalse(ship.removeGood(TradeGood.FURS));
        assertEquals(15, (int) ship.getCapacity());
    }


}
