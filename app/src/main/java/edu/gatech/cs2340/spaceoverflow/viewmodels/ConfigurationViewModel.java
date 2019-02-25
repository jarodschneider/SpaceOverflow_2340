package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.arch.lifecycle.AndroidViewModel;
import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import edu.gatech.cs2340.spaceoverflow.model.Player;
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
        String universeString = universe.toString();
        StringBuilder universeSB = new StringBuilder();

        largeLog("ConfigurationViewModel", universe.toString());
        Log.i("ConfigurationViewModel", "The player was created with 1000 credits and a Gnat spaceship. " + player.toString());
    }

    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.d(tag, content);
        }
    }

}
