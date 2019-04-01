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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView currentFuel;
    private TextView newTechLevel;
    private TextView newResourceLevel;
    private TextView distance;

    private String solarArray[] = Universe.getInstance().getSolarSystemNames();
    private SolarSystem viewedSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        solarSystems = findViewById(R.id.solar_spinner);
        currentFuel = findViewById(R.id.currentFuel);
        newTechLevel = findViewById(R.id.new_tech_level);
        newResourceLevel = findViewById(R.id.new_resource_level);
        distance = findViewById(R.id.new_distance);




        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, solarArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        solarSystems.setAdapter(adapter);

        solarSystems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewedSystem = Universe.getInstance().getSolarSystemsAsList()
                        .get(Universe.getInstance().getSolarSystemsAsList()
                                .indexOf(new SolarSystem(solarArray[solarSystems.getSelectedItemPosition()],
                                        null, null, null)));

                newTechLevel.setText(viewedSystem.getTechLevel().toString());
                newResourceLevel.setText(viewedSystem.getResourceLevel().toString());
                distance.setText(viewedSystem.distanceFrom(Universe.getInstance().getPlayer().getCoords()).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        currentFuel.setText(Universe.getInstance().getPlayer().getShip().getFuel().toString());
        viewedSystem = Universe.getInstance().getSolarSystemsAsList()
                .get(Universe.getInstance().getSolarSystemsAsList()
                        .indexOf(new SolarSystem(solarArray[solarSystems.getSelectedItemPosition()],
                                null, null, null)));

        newTechLevel.setText(viewedSystem.getTechLevel().toString());
        newResourceLevel.setText(viewedSystem.getResourceLevel().toString());
        distance.setText(viewedSystem.distanceFrom(Universe.getInstance().getPlayer().getCoords()).toString());
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
        List<SolarSystem> solarSystems = Universe.getInstance().getSolarSystemsAsList();
        SolarSystem newSystem = solarSystems.get(solarSystems.indexOf(new SolarSystem(nameCurrent, null, null, null)));
        SolarSystem oldSystem = Universe.getInstance()
                .getSolarSystems()[Universe.getInstance().getPlayer().getCoords()[0]]
                [Universe.getInstance().getPlayer().getCoords()[1]];
        if (!newSystem.equals(oldSystem)) {
            if (!Universe.getInstance().getPlayer().getShip().travelTo(newSystem)) {
                int fuelNeeded = newSystem.distanceFrom(oldSystem.getCoords());
                Toast.makeText(this,
                        String.format("Cannot travel to %s with %d fuel (%d fuel required)",
                                newSystem.getName(), Universe.getInstance().getPlayer().getShip().getFuel(),
                                fuelNeeded),
                        Toast.LENGTH_SHORT).show();
            } else {
                finish();
            }
        } else {
            Toast.makeText(this, "Must select different System than current to travel",
                    Toast.LENGTH_SHORT).show();
        }
    }
}