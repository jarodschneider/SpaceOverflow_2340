package edu.gatech.cs2340.spaceoverflow.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.Universe;
import edu.gatech.cs2340.spaceoverflow.viewmodels.UniverseViewModel;

public class UniverseActivity extends AppCompatActivity {

    private UniverseViewModel viewModel;

    private TextView name;
    private TextView pilot;
    private TextView fighter;
    private TextView trader;
    private TextView engineer;
    private TextView solarSystem;
    private Button buyButton;
    private Button sellButton;
    private TextView ship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        name = findViewById(R.id.name_text);
        pilot = findViewById(R.id.pilot_points);
        fighter = findViewById(R.id.fighter_points);
        trader = findViewById(R.id.trader_points);
        engineer = findViewById(R.id.engineer_points);
        solarSystem = findViewById(R.id.planet_text);
        buyButton = findViewById(R.id.goBuyButton);
        sellButton = findViewById(R.id.goSellButton);
        ship = findViewById(R.id.ship_text);

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);

        name.setText(Universe.getInstance().getPlayer().getName());
        pilot.setText(Universe.getInstance().getPlayer().getPilotSkill().toString());
        fighter.setText(Universe.getInstance().getPlayer().getFighterSkill().toString());
        trader.setText(Universe.getInstance().getPlayer().getTraderSkill().toString());
        engineer.setText(Universe.getInstance().getPlayer().getEngineerSkill().toString());
        viewModel.initializePlayerLocation();
        solarSystem.setText(viewModel.getPlanet().getName());
        ship.setText(Universe.getInstance().getPlayer().getShip().getName());
    }

    public void onBuyGoodsPressed(View view) {
        Log.d("Universe", "Buy goods pressed");

        Universe.getInstance().getSolarSystems()[Universe.getInstance().getPlayer().getCoords()[0]]
                                                [Universe.getInstance().getPlayer().getCoords()[1]]
                                                .initializeMarket();

        Intent intent = new Intent(UniverseActivity.this, MarketActivity.class);
        startActivity(intent);
    }

    public void onSellGoodsPressed(View view) {
        Universe.getInstance().getSolarSystems()[Universe.getInstance().getPlayer().getCoords()[0]]
                                                [Universe.getInstance().getPlayer().getCoords()[1]]
                                                .initializeMarket();

        Intent intent = new Intent(UniverseActivity.this, SellActivity.class);
        startActivity(intent);
    }
}
