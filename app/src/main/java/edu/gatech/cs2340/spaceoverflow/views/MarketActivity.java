package edu.gatech.cs2340.spaceoverflow.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.Universe;
import edu.gatech.cs2340.spaceoverflow.viewmodels.MarketViewModel;

public class MarketActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager llm;

    private MarketViewModel viewModel;

    private GoodAdapter adapter;

    private TextView marketName;
    private TextView credits;
    private TextView capacity;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        marketName = findViewById(R.id.market_name);
        credits = findViewById(R.id.credits);
        capacity = findViewById(R.id.capacity);
        backButton = findViewById(R.id.back_from_buying_button);

        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);

        rv = findViewById(R.id.trades_rv);
        rv.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        adapter = new GoodAdapter();
        rv.setAdapter(adapter);

        marketName.setText(viewModel.getSolarSystem().getName());
        updateView();
    }

    public void updateView() {
        credits.setText(Universe.getInstance().getPlayer().getCredits().toString());
        capacity.setText(Universe.getInstance().getPlayer().getShip().getCapacity().toString());
    }

    public void onBackPressed(View view) {
        finish();
    }

    public void onBooksPressed(View view) {
        updateView();
    }

    @Override
    public void onResume() {
        super.onResume();

        adapter.setGoodList(Universe.getInstance()
                .getSolarSystems()[Universe.getInstance().getPlayer().getCoords()[0]]
                [Universe.getInstance().getPlayer().getCoords()[1]]
                .getMarket().getTradeGoods());
    }
}
