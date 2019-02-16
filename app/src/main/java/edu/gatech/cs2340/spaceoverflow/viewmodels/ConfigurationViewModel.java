package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.arch.lifecycle.AndroidViewModel;
import android.app.Application;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spaceoverflow.model.Player;

public class ConfigurationViewModel extends AndroidViewModel {

    private Player player;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean validateSkillLevels() {

    }

    public void createPlayer() {

    }

}
