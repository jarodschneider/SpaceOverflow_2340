package edu.gatech.cs2340.spaceoverflow.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Universe {

    private static Universe single_instance = null;

    private List<int[]> validCoords;
    private SolarSystem[][] solarSystems = new SolarSystem[150][100];
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
        for (SolarSystem[] system: solarSystems) {
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
        for (String SolarSystemName : SolarSystemNames) {
            int coords[] = generateCoords();
            SolarSystem s = new SolarSystem(
                    SolarSystemName,
                    coords,
                    TechLevel.values()[new Random().nextInt(8)],
                    ResourceLevel.values()[new Random().nextInt(13)]);
            solarSystems[coords[0]][coords[1]] = s;
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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("android");

        myRef.child("universe").child("player").child("name").setValue(single_instance.getPlayer().getName());
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

    public void loadUniverse() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("android");
        myRef.child("universe").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                single_instance.getPlayer().setName(dataSnapshot.child("player/name").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("Name","Name:" + single_instance.getPlayer().getName());
    }

    public List<int[]> getValidCoords() {
        return validCoords;
    }

    public static void createUniverse(Player player) {
        single_instance = new Universe(player);
    }

    private int[] generateCoords() {
        Random rand = new Random();
        int x = rand.nextInt(solarSystems.length);
        int y = rand.nextInt(solarSystems[0].length);
        if (solarSystems[x][y] == null) {
            return new int[]{x, y};
        } else {
            return generateCoords();
        }
    }


    public List<SolarSystem> getSolarSystemsAsList() {
        List<SolarSystem> list = new ArrayList<>();
        for (SolarSystem[] arr : solarSystems) {
            list.addAll(Arrays.asList(arr));
        }
        return list;
    }

    public SolarSystem[][] getSolarSystems() {
        return solarSystems;
    }

    public Player getPlayer() {
        return player;
    }

    public String[] getSolarSystemNames() { return SolarSystemNames; }
}
