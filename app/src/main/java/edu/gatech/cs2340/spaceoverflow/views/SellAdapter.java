package edu.gatech.cs2340.spaceoverflow.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.gatech.cs2340.spaceoverflow.R;
import edu.gatech.cs2340.spaceoverflow.model.TradeGood;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

public class SellAdapter extends RecyclerView.Adapter<SellAdapter.SellViewHolder> {

    private List<TradeGood> goodList = Universe.getInstance()
            .getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
            .get(Universe.getInstance().getPlayer().getCoords().get(1))
            .getMarket().getTradeGoods();

    @NonNull
    @Override
    public SellAdapter.SellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sell_card, parent, false);

        return new SellViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SellAdapter.SellViewHolder holder, int position) {
        final TradeGood tradeGood = goodList.get(position);

        holder.item.setText(tradeGood.getName());
        holder.numAvailable.setText(tradeGood.getQuantity().toString());
        List<TradeGood> playerGoods = Universe.getInstance().getPlayer().getShip().getCargoHold();
        Integer playerQuantity = playerGoods.size() > position ?
                                    playerGoods.get(position).getQuantity() : 0;

        holder.numHave.setText(playerQuantity.toString());
        holder.sellButton.setText(String.format("SELL (%d credits)", tradeGood.getPrice()));

        final int finalPosition = position;

        holder.sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TradeGood> market = Universe.getInstance()
                        .getSolarSystems().get(Universe.getInstance().getPlayer().getCoords().get(0))
                        .get(Universe.getInstance().getPlayer().getCoords().get(1))
                        .getMarket().getTradeGoods();

                if (market.contains(tradeGood)) {
                    if (Universe.getInstance().getPlayer().sellGood(tradeGood)) {
                        TradeGood curr = market.get(market.indexOf(tradeGood));
                        curr.setQuantity(curr.getQuantity() + 1);
                        tradeGood.setQuantity(tradeGood.getQuantity() + 1);

                        holder.numAvailable.setText(tradeGood.getQuantity().toString());
                        List<TradeGood> playerGoods = Universe.getInstance()
                                .getPlayer().getShip().getCargoHold();

                        Integer playerQuantity = playerGoods.size() > finalPosition ?
                                playerGoods.get(finalPosition).getQuantity() : 0;

                        holder.numHave.setText(playerQuantity.toString());
                    } else {
                        Toast.makeText(view.getContext(), String.format("Can't sell with %d in hold",
                                Universe.getInstance().getPlayer().getShip()
                                        .getCargoHold().get(Universe.getInstance().getPlayer()
                                        .getShip().getCargoHold().indexOf(tradeGood)).getQuantity()),
                                Toast.LENGTH_SHORT).show();
                    }
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

    class SellViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        private Button sellButton;
        private TextView item;
        private TextView numAvailable;
        private TextView numHave;

        public SellViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.sell_card_view);
            sellButton = itemView.findViewById(R.id.sell_button);
            item = itemView.findViewById(R.id.sell_item_name);
            numAvailable = itemView.findViewById(R.id.num_available_sell);
            numHave = itemView.findViewById(R.id.num_have_sell);
            sellButton = itemView.findViewById(R.id.sell_button);
        }
    }
}
