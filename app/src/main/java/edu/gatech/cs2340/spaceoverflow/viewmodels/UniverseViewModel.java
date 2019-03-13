package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.Random;

import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

public class UniverseViewModel extends AndroidViewModel {

    public UniverseViewModel(@NonNull Application application) {
        super(application);
    }

    public SolarSystem getPlanet() {
        return Universe.getInstance()
                .getSolarSystems()[Universe.getInstance().getPlayer().getCoords()[0]]
                                    [Universe.getInstance().getPlayer().getCoords()[1]];
    }

    public void initializePlayerLocation() {
        Universe.getInstance().getPlayer().setCoords(Universe.getInstance().getValidCoords()
                .get(new Random().nextInt(Universe.getInstance().getValidCoords().size())));
    }
}
