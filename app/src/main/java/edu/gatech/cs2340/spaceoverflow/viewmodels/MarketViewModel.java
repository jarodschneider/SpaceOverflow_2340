package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

/**
 * Market ViewModel
 */
public class MarketViewModel extends AndroidViewModel {

    /**
     * ViewModel constructor
     *
     * @param application application
     */
    public MarketViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Gets current SolarSystem
     *
     * @return solar system
     */
    public SolarSystem getSolarSystem() {
        return Universe.getInstance()
                .getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
                .get(Universe.getInstance().getPlayer().getCoords().get(1));
    }
}
