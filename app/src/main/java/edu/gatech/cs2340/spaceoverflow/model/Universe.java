package edu.gatech.cs2340.spaceoverflow.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Universe class
 */
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

    /**
     * Gets singleton
     *
     * @return instance
     */
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

    /**
     * Gets list of valid coordinates
     *
     * @return coordinates list
     */
    public List<List<Integer>> getValidCoords() {
        return Collections.unmodifiableList(validCoords);
    }

    /**
     * Creates Universe
     *
     * @param player player
     */
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

    /**
     * Gets solar systems as list
     *
     * @return systems list
     */
    public List<SolarSystem> getSolarSystemsAsList() {
        List<SolarSystem> list = new ArrayList<>();
        for (List<SolarSystem> arr : solarSystems) {
            list.addAll(arr);
        }
        return list;
    }

    /**
     * Loads from Firebase
     *
     * @return Player
     */
    public Player loadUniverse() {
        Log.d("DEV", single_instance.player.getName());
        myRef.child("game").child("player").child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                single_instance.player.setName(dataSnapshot.getValue(String.class));
                Log.d("DEV", single_instance.player.getName());
            }

            public void onCancelled(DatabaseError databaseError) {
                // Getting Post
                // failed, log a message
                Log.d("DEV", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        myRef.child("game").child("player").child("pilotSkill").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                single_instance.player.setPilotSkill(dataSnapshot.getValue(Integer.class));
                Log.d("DEV", single_instance.player.getName());
            }

            public void onCancelled(DatabaseError databaseError) {
                // Getting Post
                // failed, log a message
                Log.d("DEV", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        myRef.child("game").child("player").child("fighterSkill").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                single_instance.player.setFighterSkill(dataSnapshot.getValue(Integer.class));
                Log.d("DEV", single_instance.player.getName());
            }

            public void onCancelled(DatabaseError databaseError) {
                // Getting Post
                // failed, log a message
                Log.d("DEV", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
//        myRef.child("game").child("player").child("traderSkill").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                single_instance.player.setTraderSkill(dataSnapshot.getValue(Integer.class));
//                Log.d("DEV", single_instance.player.getName());
//            }
//
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post
//                // failed, log a message
//                Log.d("DEV", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        });
//        myRef.child("game").child("player").child("engineerSkill").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                single_instance.player.setEngineerSkill(dataSnapshot.getValue(Integer.class));
//                Log.d("DEV", single_instance.player.getName());
//            }
//
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post
//                // failed, log a message
//                Log.d("DEV", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        });
//        myRef.child("game").child("systems").child("engineerSkill").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                single_instance.player.setEngineerSkill(dataSnapshot.getValue(Integer.class));
//                Log.d("DEV", single_instance.player.getName());
//            }
//
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post
//                // failed, log a message
//                Log.d("DEV", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        });

        Log.d("DEV", single_instance.player.getName());
        return single_instance.player;
    }

    /**
     * Gets list of SolarSystems
     * @return list of systems
     */
    public List<List<SolarSystem>> getSolarSystems() {
        return solarSystems;
    }

    /**
     * Gets player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets system names
     *
     * @return system names
     */
    public String[] getSolarSystemNames() { return SolarSystemNames.clone(); }
}
