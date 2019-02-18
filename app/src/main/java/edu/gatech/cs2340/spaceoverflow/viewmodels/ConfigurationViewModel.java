package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.arch.lifecycle.AndroidViewModel;
import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import edu.gatech.cs2340.spaceoverflow.model.Player;

public class ConfigurationViewModel extends AndroidViewModel {

    private Player player;
    private final int TOTAL_SKILL = 16;


    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean validateSkillLevels(int pilotSkill, int fighterSkill, int traderSkill,
                                       int engineerSkill) {
        if (pilotSkill < 0) {
            return false;
        }
        if (fighterSkill < 0) {
            return false;
        }
        if (traderSkill < 0) {
            return false;
        }
        if (engineerSkill < 0) {
            return false;
        }
        if ((pilotSkill + fighterSkill + traderSkill + engineerSkill) != TOTAL_SKILL) {
            return false;
        }
        return true;
    }

    public void createPlayer(String name, int pilotSkill, int fighterSkill, int traderSkill,
                             int engineerSkill) {
        player = new Player(name, pilotSkill, fighterSkill, traderSkill, engineerSkill);
        Log.d("INFO", "The player was created." + player.toString());
    }

}
