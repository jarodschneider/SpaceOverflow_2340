package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

/**
 * Selling ViewModel
 */
public class SellViewModel extends AndroidViewModel {

    /**
     * ViewModel constructor
     * @param application application
     */
    public SellViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Gets player's current SolarSystem
     * @return solar system
     */
    public SolarSystem getSolarSystem() {
        return Universe.getInstance()
                .getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
                .get(Universe.getInstance().getPlayer().getCoords().get(1));
    }
}
