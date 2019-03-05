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
import edu.gatech.cs2340.spaceoverflow.model.Player;
import edu.gatech.cs2340.spaceoverflow.model.Universe;
import edu.gatech.cs2340.spaceoverflow.viewmodels.UniverseViewModel;

public class UniverseActivity extends AppCompatActivity {

    private UniverseViewModel viewModel;

    private TextView name;
    private TextView solarSystem;
    private Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        name = findViewById(R.id.name_text);
        solarSystem = findViewById(R.id.planet_text);
        buyButton = findViewById(R.id.goBuyButton);

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);

        name.setText(Player.getInstance().getName());
    }

    public void onBuyGoodsPressed(View view) {
        Log.d("Universe", "Buy goods pressed");

        Intent intent = new Intent(UniverseActivity.this, MarketActivity.class);
        startActivity(intent);
    }
}
