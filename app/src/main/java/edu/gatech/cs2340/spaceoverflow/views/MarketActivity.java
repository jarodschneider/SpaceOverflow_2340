package edu.gatech.cs2340.spaceoverflow.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

public class MarketActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager llm;

    private GoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        rv = findViewById(R.id.trades_rv);
        rv.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        adapter = new GoodAdapter();
        rv.setAdapter(adapter);
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
