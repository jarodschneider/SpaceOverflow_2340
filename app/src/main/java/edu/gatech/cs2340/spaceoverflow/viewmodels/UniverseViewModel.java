package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.Random;

import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

/**
 * Universe ViewModel
 */
public class UniverseViewModel extends AndroidViewModel {

    /**
     * ViewModel constructor
     *
     * @param application application
     */
    public UniverseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Gets current planet
     *
     * @return planet
     */
    public SolarSystem getPlanet() {
        return Universe.getInstance()
                .getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
                                    .get(Universe.getInstance().getPlayer().getCoords().get(1));
    }

    /**
     * Initializes player's initial location
     */
    public void initializePlayerLocation() {
        Universe.getInstance().getPlayer().setCoords(Universe.getInstance().getValidCoords()
                .get(new Random().nextInt(Universe.getInstance().getValidCoords().size())));

        Universe.getInstance().getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
                .get(Universe.getInstance().getPlayer().getCoords().get(1))
                .initializeMarket();
    }
}
