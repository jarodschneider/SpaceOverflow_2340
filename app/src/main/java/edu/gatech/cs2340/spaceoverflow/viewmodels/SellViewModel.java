package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

public class SellViewModel extends AndroidViewModel {

    public SellViewModel(@NonNull Application application) {
        super(application);
    }

    public SolarSystem getSolarSystem() {
        return Universe.getInstance()
                .getSolarSystems()[Universe.getInstance().getPlayer().getCoords()[0]]
                [Universe.getInstance().getPlayer().getCoords()[1]];
    }
}
