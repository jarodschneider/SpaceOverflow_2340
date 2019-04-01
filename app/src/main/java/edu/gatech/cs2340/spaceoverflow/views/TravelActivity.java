package edu.gatech.cs2340.spaceoverflow.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.ResourceLevel;
import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.TechLevel;
import edu.gatech.cs2340.spaceoverflow.model.Universe;
import edu.gatech.cs2340.spaceoverflow.viewmodels.SellViewModel;
import edu.gatech.cs2340.spaceoverflow.viewmodels.UniverseViewModel;

public class TravelActivity extends AppCompatActivity {


    private Spinner solarSystems;

    private String solarArray[] = Universe.getInstance().getSolarSystemNames();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        solarSystems = findViewById(R.id.solar_spinner);




        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, solarArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        solarSystems.setAdapter(adapter);

    }


    public void onWarp(View view) {
        //String name, int[] coords, TechLevel techLevel, ResourceLevel resourceLevel
     //   SolarSystem systems[][] = Universe.getInstance().getSolarSystems();
        String nameCurrent = solarArray[solarSystems.getSelectedItemPosition()];
//        Log.d("DEV", String.valueOf(solarArray.length));
//        if (solarSystems.getSelectedItemPosition() >= 0 && solarSystems.getSelectedItemPosition() < solarArray.length) {
//            for (SolarSystem[] system: systems) {
//                for (SolarSystem system2: system) {
//                    if (system != null) {
////                        String name = system2.getName();
////
////                        if (name.equals(nameCurrent)) {
////                            Log.d("DEV", "FOUND PLANET ***");
////                            Log.d("DEV", solarArray[solarSystems.getSelectedItemPosition()]);
////                            //Universe.getInstance().getPlayer().getShip().travelTo(system2);
////                            finish();
////                        }
//                    }
//
//                }
//            }
//        }
        //can someone do this? Find the coordinates for the planet selected to warp to
        int coords[] = new int[2];
        coords[0] = 0;
        coords[1] = 0;
        //

        Universe.getInstance().getPlayer().getShip().travelTo(new SolarSystem(nameCurrent, coords,
                TechLevel.values()[new Random().nextInt(8)],
                ResourceLevel.values()[new Random().nextInt(13)]));
        //make toast
        finish();

    }
}