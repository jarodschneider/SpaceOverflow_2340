package edu.gatech.cs2340.spaceoverflow.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Random;

import edu.gatech.cs2340.spaceoverflow.model.TradeGood;
import edu.gatech.cs2340.spaceoverflow.model.Universe;

public class EventViewModel extends AndroidViewModel {

    public EventViewModel(@NonNull Application application) {
        super(application);
    }

    public String generateEvent() {
        Random random = new Random();

        boolean police = random.nextInt(2) == 1;

        String msg = "";

        if (police) {
            msg += "Halt! This is the Galactic Security Force!";

            boolean foundContraband = false;

            List<TradeGood> hold = Universe.getInstance().getPlayer().getShip().getCargoHold();

            if (hold.get(hold.indexOf(TradeGood.FIREARMS)).getQuantity() > 0) {
                foundContraband = true;

                Universe.getInstance().getPlayer().getShip()
                        .setCapacity(Universe.getInstance().getPlayer().getShip().getCapacity()
                                + hold.get(hold.indexOf(TradeGood.FIREARMS)).getQuantity());

                hold.get(hold.indexOf(TradeGood.FIREARMS)).setQuantity(0);

                Universe.getInstance().getPlayer()
                        .setCredits(Universe.getInstance().getPlayer().getCredits() - 500);

                msg += "\n\nYou have been found in possession of illegal firearms!"
                        + " This contraband has been seized and you have been fined 500 credits!";
            }

            if (hold.get(hold.indexOf(TradeGood.NARCOTICS)).getQuantity() > 0) {
                foundContraband = true;

                Universe.getInstance().getPlayer().getShip()
                        .setCapacity(Universe.getInstance().getPlayer().getShip().getCapacity()
                                + hold.get(hold.indexOf(TradeGood.NARCOTICS)).getQuantity());

                hold.get(hold.indexOf(TradeGood.NARCOTICS)).setQuantity(0);

                Universe.getInstance().getPlayer()
                        .setCredits(Universe.getInstance().getPlayer().getCredits() - 500);

                msg += "\n\nYou have been found in possession of illegal narcotics!"
                        + " This contraband has been seized and you have been fined 500 credits!";
            }

            if (!foundContraband) {
                msg += "\n\nYou're clear! Continue to your destination!";
            }

            return msg;
        } else {
            return "You didn't encounter anyone... this time.";
        }
    }
}
