package edu.gatech.cs2340.spaceoverflow.model;

import java.util.Arrays;
import java.util.Random;

public class Universe {

    private SolarSystem[][] solarSystems = new SolarSystem[150][100];
    private Player player;

    private static final String SolarSystemNames[] = {
            "Acamar",
            "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
            "Aldea",
            "Andevian",
            "Antedi",
            "Balosnee",
            "Baratas",
            "Brax",			// One of the heroes in Master of Magic
            "Bretel",		// This is a Dutch device for keeping your pants up.
            "Calondia"};

    @Override
    public String toString() {
        return Arrays.deepToString(solarSystems);
    }

    public Universe(Player player) {
        this.player = player;
        for (String SolarSystemName : SolarSystemNames) {
            int coords[] = generateCoords();
            SolarSystem s = new SolarSystem(
                    SolarSystemName,
                    coords,
                    TechLevel.values()[new Random().nextInt(8)],
                    ResourceLevel.values()[new Random().nextInt(13)]);
            solarSystems[coords[0]][coords[1]] = s;
        }
    }

    private int[] generateCoords() {
        Random rand = new Random();
        int x = rand.nextInt(solarSystems.length);
        int y = rand.nextInt(solarSystems[0].length);
        if (solarSystems[x][y] == null) {
            return new int[]{x, y};
        } else {
            return generateCoords();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public SolarSystem[][] getSolarSystems() {
        return solarSystems;
    }
}
