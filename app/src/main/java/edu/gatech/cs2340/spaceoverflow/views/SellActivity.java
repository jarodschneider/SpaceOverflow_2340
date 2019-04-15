package edu.gatech.cs2340.spaceoverflow.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.Universe;
import edu.gatech.cs2340.spaceoverflow.viewmodels.SellViewModel;

/**
 * Selling activity
 */
public class SellActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager llm;

    private SellViewModel viewModel;

    private SellAdapter adapter;

    private TextView marketName;
    private TextView credits;
    private TextView capacity;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        marketName = findViewById(R.id.market_name_sell);
        credits = findViewById(R.id.credits_sell);
        capacity = findViewById(R.id.capacity_sell);
        backButton = findViewById(R.id.back_from_selling_button);

        viewModel = ViewModelProviders.of(this).get(SellViewModel.class);

        rv = findViewById(R.id.trades_rv_sell);
        rv.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        adapter = new SellAdapter();
        rv.setAdapter(adapter);

        marketName.setText(viewModel.getSolarSystem().getName());
        updateView();
    }

    /**
     * Updates view
     */
    public void updateView() {
        credits.setText(Universe.getInstance().getPlayer().getCredits().toString());
        capacity.setText(Universe.getInstance().getPlayer().getShip().getCapacity().toString());
    }

    /**
     * Ends activity
     *
     * @param view current View
     */
    public void onBackPressed(View view) {
        finish();
    }

    /**
     * Updates view to reflect books
     *
     * @param view current View
     */
    public void onBooksPressed(View view) {
        updateView();
    }

    @Override
    public void onResume() {
        super.onResume();

        adapter.setGoodList(Universe.getInstance()
                .getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
                .get(Universe.getInstance().getPlayer().getCoords().get(1))
                .getMarket().getTradeGoods());
    }
}
