package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.arch.lifecycle.AndroidViewModel;
import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import edu.gatech.cs2340.spaceoverflow.model.Player;
import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

public class ConfigurationViewModel extends AndroidViewModel {

    private Universe universe;
    private Player player;
    private final int TOTAL_SKILL = 16;

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
        universe = new Universe(player);
        largeLog("ConfigurationViewModel", universe.toString());

        for (SolarSystem[] sA: universe.getSolarSystems()) {
            for (SolarSystem s: sA) {
                if (s != null) {
                    Log.i("ConfigurationViewModel", s.toString());
                }
            }
        }

        Log.i("ConfigurationViewModel", "The player was created with 1000 credits and a Gnat spaceship. " + player.toString());
    }

    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.i(tag, " \n" + content.substring(0, 4000 - 61));
            largeLog(tag, " \n" + content.substring(4000 - 61));
        } else {
            Log.i(tag, " \n" + content);
        }
    }

}
