package com.triercityhack19.logic;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class Schedule
{
    public static int searchWindow = 60 * 60; // 120 Minuten plusminus

    ArrayList<Ride> rides = new ArrayList<Ride>();

    // Initialisiere Rundfahrten
    public Schedule()
    {
        // Jeden Tag
        for (long daystart = 1574204400; daystart< 1575932401; daystart += 86400) // 1574204400 = 20.11.2019 um 00:00 bis 1575932400 = 10.12.2019 kurz nach 00:00
        {
            Ride template;
            long duration;

            // Gerolstein - Kopp
            template = Topology.directRides[0].clone();
            addThreeEntries(template, daystart);

            // Gerolstein - Birresborn
            template = Topology.directRides[11].clone();
            addThreeEntries(template, daystart);

            // Gerolstein Mürlenbach
            duration = Topology.directRides[0].getStarttime()
                    + Topology.directRides[2].getStarttime()
                    + Topology.directRides[4].getStarttime();
            template = new Ride("Gerolstein", "Mürlenbach", duration);
            addThreeEntries(template, daystart);

            // Gerolstein Salm
            template = Topology.directRides[8].clone();
            addThreeEntries(template, daystart);

            // Kopp - Birresborn
            template = Topology.directRides[2].clone();
            addThreeEntries(template, daystart);

            // Kopp - Mürlenbach
            duration = Topology.directRides[2].getStarttime()
                    + Topology.directRides[4].getStarttime();
            template = new Ride("Kopp", "Mürlenbach", duration);
            addThreeEntries(template, daystart);

            // Kopp - Salm
            duration = Topology.directRides[2].getStarttime()
                    + Topology.directRides[4].getStarttime()
                    + Topology.directRides[6].getStarttime();
            template = new Ride("Kopp", "Salm", duration);
            addThreeEntries(template, daystart);

            // Kopp - Gerolstein
            duration = Topology.directRides[2].getStarttime()
                    + Topology.directRides[4].getStarttime()
                    + Topology.directRides[6].getStarttime()
                    + Topology.directRides[8].getStarttime();
            template = new Ride("Kopp", "Gerolstein", duration);

            // Birresborn - Mürlenbach
            template = Topology.directRides[4].clone();
            addThreeEntries(template, daystart);

            // Birresborn - Salm
            duration = Topology.directRides[4].getStarttime()
                    + Topology.directRides[6].getStarttime();
            template = new Ride("Birresborn", "Salm", duration);
            addThreeEntries(template, daystart);

            // Birresborn - Gerolstein
            duration = Topology.directRides[4].getStarttime()
                    + Topology.directRides[6].getStarttime()
                    + Topology.directRides[8].getStarttime();
            template = new Ride("Birresborn", "Gerolstein", duration);
            addThreeEntries(template, daystart);

            // Mürlenbach - Salm
            template = Topology.directRides[6].clone();
            addThreeEntries(template, daystart);

            // Mürlenbach - Gerolstein
            duration = Topology.directRides[6].getStarttime()
                    + Topology.directRides[8].getStarttime();
            template = new Ride("Mürlenbach", "Gerolstein", duration);
            addThreeEntries(template, daystart);

            // Salm - Gerolstein
            template = Topology.directRides[9].clone();
            addThreeEntries(template, daystart);
        }
    }

    private void addThreeEntries (Ride template, long daystart)
    {
        long[] dalytimes = {9 * 3600, 14 * 3600, 18 * 3600}; // 9h, 14h, 18h

        for (long time : dalytimes)
        {
            Ride toAdd = template.clone();
            toAdd.setStarttime(daystart + toAdd.getStarttime() + time);
            toAdd.setDriver(Topology.provider);
            rides.add(toAdd);
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
