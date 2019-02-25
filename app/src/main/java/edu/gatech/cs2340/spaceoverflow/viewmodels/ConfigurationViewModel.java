package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.arch.lifecycle.AndroidViewModel;
import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Random;

import edu.gatech.cs2340.spaceoverflow.model.Player;
import edu.gatech.cs2340.spaceoverflow.model.ResourceLevel;
import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.TechLevel;

public class ConfigurationViewModel extends AndroidViewModel {

    private Player player;
    private final int TOTAL_SKILL = 16;

    private static final String SolarSystemName[] = {
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

    private SolarSystem[][] universe = new SolarSystem[150][100];


    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean validateSkillLevels(Player player) {
        if (player.getPilotSkill() < 0) {
            return false;
        }
        if (player.getFighterSkill() < 0) {
            return false;
        }
        if (player.getTraderSkill()< 0) {
            return false;
        }
        if (player.getEngineerSkill() < 0) {
            return false;
        }
        return (player.getPilotSkill()
                + player.getFighterSkill()
                + player.getTraderSkill()
                + player.getEngineerSkill())
                == TOTAL_SKILL;
    }

    public Integer skillsRemaining(Player player) {
        return 16 - (player.getPilotSkill()
                    + player.getFighterSkill()
                    + player.getTraderSkill()
                    + player.getEngineerSkill());
    }

    public void createPlayer(Player player) {
        this.player = player;
        createUniverse();
        Log.i("ConfigurationViewModel", "The player was created with 1000 credits and a Gnat spaceship. " + player.toString());
    }

    private void createUniverse() {
        for (int i = 0; i < 9; i++) {
            int coords[] = generateCoords();
            SolarSystem s = new SolarSystem(
                    SolarSystemName[i],
                    coords,
                    TechLevel.values()[new Random().nextInt(8)],
                    ResourceLevel.values()[new Random().nextInt(13)]);
            universe[coords[0]][coords[1]] = s;
            Log.i("ConfigurationViewModel", "SolarSystem created! " + s.toString());
        }
    }

    private int[] generateCoords() {
        Random rand = new Random();
        int x = rand.nextInt(universe.length);
        int y = rand.nextInt(universe[0].length);
        if (universe[x][y] == null) {
            return new int[]{x, y};
        } else {
            return generateCoords();
        }
    }

}
