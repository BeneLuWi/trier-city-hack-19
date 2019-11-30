package com.triercityhack19.logic;

import org.springframework.stereotype.Component;

import java.util.HashMap;

public class Topology {

    public static String[] locations = {"Kopp", "Birresborn", "Mürlenbach", "Salm"};
    public static String hq = "Gerolstein";
    public static String city = "Gerolstein";
    public static String provider = "PROVIDER";
    public static long defaultstamp = 1575240843;

    // Theoretically this would have to be a graph but that would require a* or at least greedy search.
    public static Ride [] directRides = {

            new Ride("Gerolstein","Kopp", (long) 15), //0
            new Ride("Kopp","Gerolstein", (long) 15),

            new Ride("Kopp","Birresborn", (long) 15), //2
            new Ride("Birresborn","Kopp", (long) 15),

            new Ride("Birresborn","Mürlenbach", (long) 15), // 4
            new Ride("Mürlenbach","Birresborn", (long) 15),

            new Ride("Mürlenbach","Salm", (long) 15), // 6
            new Ride("Salm","Mürlenbach", (long) 15),

            new Ride("Gerolstein","Salm", (long) 15), //8
            new Ride("Salm","Gerolstein", (long) 15),

            new Ride("Birresborn","Gerolstein", (long) 15), //10
            new Ride("Gerolstein","Birresborn", (long) 15)
    };

    public static Ride [] indirectRides = {

            new Ride("Mürlenbach","Gerolstein", (long) 15),
            new Ride("Gerolstein","Mürlenbach", (long) 15),

    };

    public static HashMap<Ride, Ride[]> autoAdd = new HashMap<Ride, Ride[]>() {{
        put(indirectRides[0], new Ride[] {directRides[5], directRides[10]});
        put(indirectRides[1], new Ride[] {directRides[11], directRides[4]});
    }};
}

