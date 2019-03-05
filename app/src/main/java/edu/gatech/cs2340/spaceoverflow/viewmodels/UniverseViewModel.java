package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spaceoverflow.model.Universe;

public class UniverseViewModel extends AndroidViewModel {

    private Universe universe;

    public UniverseViewModel(@NonNull Application application) {
        super(application);
    }

    public Universe getUniverse() {
        return universe;
    }
}
