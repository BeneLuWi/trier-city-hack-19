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

            new Ride("Gerolstein","Kopp", (long) 15 * 3600), //0
            new Ride("Kopp","Gerolstein", (long) 15 * 3600),

            new Ride("Kopp","Birresborn", (long) 5 * 3600), //2
            new Ride("Birresborn","Kopp", (long) 5 * 3600),

            new Ride("Birresborn","Mürlenbach", (long) 5 * 3600), // 4
            new Ride("Mürlenbach","Birresborn", (long) 5 * 3600),

            new Ride("Mürlenbach","Salm", (long) 10 * 3600), // 6
            new Ride("Salm","Mürlenbach", (long) 10 * 3600),

            new Ride("Gerolstein","Salm", (long) 25 * 3600), //8
            new Ride("Salm","Gerolstein", (long) 25 * 3600),

            new Ride("Birresborn","Gerolstein", (long) 10 * 3600), //10
            new Ride("Gerolstein","Birresborn", (long) 10 * 3600)
    };

    public static Ride [] indirectRides = {

            new Ride("Mürlenbach","Gerolstein", directRides[5].getStarttime() + directRides[10].getStarttime()),
            new Ride("Gerolstein","Mürlenbach", directRides[11].getStarttime() + directRides[4].getStarttime()),

    };

    public static HashMap<Ride, Ride[]> autoAdd = new HashMap<Ride, Ride[]>() {{
        put(indirectRides[0], new Ride[] {directRides[5], directRides[10]});
        put(indirectRides[1], new Ride[] {directRides[11], directRides[4]});
    }};
}

