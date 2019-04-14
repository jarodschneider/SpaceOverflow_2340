package edu.gatech.cs2340.spaceoverflow.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.SolarSystem;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

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
        String nameCurrent = solarArray[solarSystems.getSelectedItemPosition()];

        List<SolarSystem> solarSystems = Universe.getInstance().getSolarSystemsAsList();
        SolarSystem newSystem = solarSystems.get(solarSystems.indexOf(new SolarSystem(nameCurrent, null, null, null)));
        SolarSystem oldSystem = Universe.getInstance()
                .getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
                .get(Universe.getInstance().getPlayer().getCoords().get(1));
        if (!newSystem.equals(oldSystem)) {
            if (!Universe.getInstance().getPlayer().getShip().travelTo(newSystem)) {
                int fuelNeeded = newSystem.distanceFrom(oldSystem.getCoords());
                Toast.makeText(this,
                        String.format("Cannot travel to %s with %d fuel (%d fuel required)",
                                newSystem.getName(), Universe.getInstance().getPlayer().getShip().getFuel(),
                                fuelNeeded),
                        Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(TravelActivity.this, EventActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            Toast.makeText(this, "Must select different System than current to travel",
                    Toast.LENGTH_SHORT).show();
        }
    }
}