package edu.gatech.cs2340.spaceoverflow.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Universe {

    private static Universe single_instance;

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference();

    private List<List<Integer>> validCoords;
    private List<List<SolarSystem>> solarSystems = new ArrayList<>();
    private Player player;

    private static final String SolarSystemNames[] = {
            "Acamar",
            "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
            "Aldea",
            "Andevian",
            "Antedi",
            "Balosnee",
            "Baratas",
            "Brax",			// One of the heroes in Master of Magic
            "Bretel",		// This is a Dutch device for keeping your pants up.
            "Calondia"};

    @Override
    public String toString() {

        StringBuilder universeSB = new StringBuilder();
        for (List<SolarSystem> system: solarSystems) {
            for (SolarSystem system2: system) {
                if (system2 == null) {
                    universeSB.append("-");
                } else {
                    universeSB.append("X");
                }
            }
            universeSB.append("\n");

        }
        return universeSB.toString();
    }

    private Universe(Player player) {
        this.player = player;
        validCoords = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            solarSystems.add(new ArrayList<SolarSystem>());
            for (int j = 0; j < 100; j++) {
                solarSystems.get(i).add(null);
            }
        }

        for (String SolarSystemName : SolarSystemNames) {
            List<Integer> coords = generateCoords();
            SolarSystem s = new SolarSystem(
                    SolarSystemName,
                    coords,
                    TechLevel.values()[new Random().nextInt(8)],
                    ResourceLevel.values()[new Random().nextInt(13)]);
            solarSystems.get(coords.get(0)).set(coords.get(1), s);
            validCoords.add(coords);
        }
    }

    public static Universe getInstance() {
        if (single_instance == null) {
            single_instance = new Universe(new Player("", 0,
                                                               0,
                                                                0,
                                                              0));
        }

        return single_instance;
    }
//
//    private static void saveAsText() {
//        try {
//            PrintWriter pw = new PrintWriter(file);
//            sm.saveAsText(pw);
//            pw.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Log.d("UserManagerFacade", "Error opening the text file for save!");
//            return false;
//        }
//
//    }

    public List<List<Integer>> getValidCoords() {
        return validCoords;
    }

    public static void createUniverse(Player player) {
        single_instance = new Universe(player);

        myRef.child("game").child("player").setValue(single_instance.player);
        myRef.child("game").child("systems").setValue(single_instance.solarSystems);
        myRef.child("game").child("validCoords").setValue(single_instance.validCoords);
    }

    private List<Integer> generateCoords() {
        Random rand = new Random();
        int x = rand.nextInt(solarSystems.size());
        int y = rand.nextInt(solarSystems.get(0).size());
        if (solarSystems.get(x).get(y) == null) {
            List<Integer> generated = new ArrayList<>();
            generated.add(x);
            generated.add(y);
            return generated;
        } else {
            return generateCoords();
        }
    }


    public List<SolarSystem> getSolarSystemsAsList() {
        List<SolarSystem> list = new ArrayList<>();
        for (List<SolarSystem> arr : solarSystems) {
            list.addAll(arr);
        }
        return list;
    }

    public List<List<SolarSystem>> getSolarSystems() {
        return solarSystems;
    }

    public Player getPlayer() {
        return player;
    }

    public String[] getSolarSystemNames() { return SolarSystemNames; }
}
