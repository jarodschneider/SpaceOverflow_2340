package edu.gatech.cs2340.spaceoverflow.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spaceoverflow.model.TradeGood;
import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.GoodViewHolder> {

    private List<TradeGood> goodList = new ArrayList<>();

    @NonNull
    @Override
    public GoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.good_card, parent, false);

        return new GoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodViewHolder holder, int position) {
        final TradeGood tradeGood = goodList.get(position);

        holder.item.setText(tradeGood.getName());
        holder.numAvailable.setText(tradeGood.getQuantity().toString());
        List<TradeGood> playerGoods = Universe.getInstance().getPlayer().getShip().getCargoHold();
        Integer playerQuantity = playerGoods.size() > position ? playerGoods.get(position).getQuantity() : 0;
        holder.numHave.setText(playerQuantity.toString());
        holder.buyButton.setText(String.format("BUY (%d credits)", tradeGood.getPrice()));

        final int finalPosition = position;

        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Universe.getInstance().getPlayer().buyGood(tradeGood)) {
                    holder.numAvailable.setText(tradeGood.getQuantity().toString());

                    List<TradeGood> playerGoods = Universe.getInstance()
                            .getPlayer().getShip().getCargoHold();

                    Integer playerQuantity = playerGoods.size() > finalPosition ?
                                                playerGoods.get(finalPosition).getQuantity() : 0;

                    holder.numHave.setText(playerQuantity.toString());
                } else {
                    Toast.makeText(view.getContext(),
                            String.format("Can't buy with %d credits and %d remaining capacity",
                                    Universe.getInstance().getPlayer().getCredits(),
                                    Universe.getInstance().getPlayer().getShip().getCapacity()),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return Universe.getInstance()
                .getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
                .get(Universe.getInstance().getPlayer().getCoords().get(1))
                .getMarket().getTradeGoods().size();
    }

    public void setGoodList(List<TradeGood> goods) {
        goodList = goods;
        notifyDataSetChanged();
    }

    class GoodViewHolder extends RecyclerView.ViewHolder {
        private Button buyButton;
        private TextView item;
        private TextView numAvailable;
        private TextView numHave;

        public GoodViewHolder(@NonNull View itemView) {
            super(itemView);
            buyButton = itemView.findViewById(R.id.buy_button);
            item = itemView.findViewById(R.id.item_name);
            numAvailable = itemView.findViewById(R.id.num_available);
            numHave = itemView.findViewById(R.id.num_have);
            buyButton = itemView.findViewById(R.id.buy_button);
        }
    }
}
