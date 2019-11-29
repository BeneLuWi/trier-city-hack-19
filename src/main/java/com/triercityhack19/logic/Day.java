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
    public static int searchWindow = 60 * 60000; // 120 Minuten plusminus

    ArrayList<Ride> rides = new ArrayList<Ride>();

    // Initialisiere Paketauslieferung
    public Day ()
    {
        java.text.DateFormat df = new java.text.SimpleDateFormat("dd-M-yyyy hh:mm");

        // Lieferfahrten
        for (String location: Hardcoded.locations)
        {
            try {
                addRide(new Ride(Hardcoded.hq, location, df.parse(Hardcoded.date + " 10:00")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Ride> search (Ride toSearch)
    {

        ArrayList<Ride> toReturn = new ArrayList<>(rides);
        System.out.println(toReturn.get(0).getStarttime().getTime());
        System.out.println(toSearch.getStarttime().getTime());
        System.out.println(Math.abs(toReturn.get(0).getStarttime().getTime() - toSearch.getStarttime().getTime()));
        toReturn.removeIf(c -> (
                !c.getStart().equals(toSearch.getStart())
                    ||
                !c.getDest().equals(toSearch.getDest())
                    ||
                Math.abs(c.getStarttime().getTime() - toSearch.getStarttime().getTime()) > searchWindow
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
