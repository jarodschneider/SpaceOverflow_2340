package edu.gatech.cs2340.spaceoverflow;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spaceoverflow.model.Player;
import edu.gatech.cs2340.spaceoverflow.model.ResourceLevel;
import edu.gatech.cs2340.spaceoverflow.model.Ship;
import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.TechLevel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SunnyJUnitTest {
    private Player tester;
    private SolarSystem currentLocation;
    private SolarSystem validPlanet;
    private SolarSystem distantPlanet;

    @Before
    public void init() {
        tester = new Player("Test", 0, 0, 0, 0);
        tester.setShip(Ship.GNAT);

        List<Integer> acamar = new ArrayList<>();
        acamar.add(0);
        acamar.add(0);

        List<Integer> adahn = new ArrayList<>();
        adahn.add(0);
        adahn.add(50);

        List<Integer> aldea = new ArrayList<>();
        aldea.add(0);
        aldea.add(135);

        currentLocation = new SolarSystem("acamar", acamar, TechLevel.HI_TECH, ResourceLevel.ARTISTIC);
        validPlanet = new SolarSystem("adahn", adahn, TechLevel.HI_TECH, ResourceLevel.ARTISTIC);
        distantPlanet = new SolarSystem("aldea", aldea, TechLevel.HI_TECH, ResourceLevel.ARTISTIC);

       tester.setCurrentSystem(currentLocation);
    }

    @Test
    public void travelToNullTest() {
        assertFalse(tester.getShip().travelTo(null, tester));
    }

    @Test
    public void nullTravelingPlayerTest() {
        assertFalse(tester.getShip().travelTo(validPlanet, null));
    }

    @Test
    public void travelToCurrentPlanetTest() {
        assertFalse(tester.getShip().travelTo(currentLocation, tester));
    }

    @Test
    public void travelToValidPlanetTest() {
        assertTrue(tester.getShip().travelTo(validPlanet, tester));
    }

    @Test
    public void travelTooFarTest() {
        assertFalse(tester.getShip().travelTo(distantPlanet, tester));
    }

}