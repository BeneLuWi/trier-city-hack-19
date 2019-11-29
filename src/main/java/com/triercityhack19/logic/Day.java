package com.triercityhack19.logic;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TreeSet;

@Component
public class Day
{
    public static int searchWindow = 60 * 60; // 120 Minuten plusminus

    ArrayList<Ride> rides = new ArrayList<Ride>();

    // Initialisiere Paketauslieferung
    public Day ()
    {
        // Lieferfahrten
        for (String location: Hardcoded.locations)
        {
            addRide(new Ride(Hardcoded.hq, location, Hardcoded.defaultstamp, Hardcoded.provider));
        }
    }

    public ArrayList<Ride> search (Ride toSearch)
    {

        ArrayList<Ride> toReturn = new ArrayList<>(rides);
        System.out.println(toReturn.get(0).getStarttime());
        System.out.println(toSearch.getStarttime());
        System.out.println(Math.abs(toReturn.get(0).getStarttime() - toSearch.getStarttime()));
        toReturn.removeIf(c -> (
                !c.getStart().equals(toSearch.getStart())
                    ||
                !c.getDest().equals(toSearch.getDest())
                    ||
                Math.abs(c.getStarttime() - toSearch.getStarttime()) > searchWindow
        ));

        return toReturn;
    }

    public Ride search (String id)
    {
        for (Ride ride: rides)
        {
            if (ride.getId().equals(id))
                return ride;
        }
        return null;
    }

    public void addRide (Ride ride)
    {
        rides.add(ride);
        Collections.sort(rides);
    }
}
