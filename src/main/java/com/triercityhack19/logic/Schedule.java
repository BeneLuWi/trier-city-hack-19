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
            long start;

            // Gerolstein - Kopp
            template = Topology.directRides[0].clone();
            template.setStarttime((long) 0);
            addThreeEntries(template, daystart);

            // Gerolstein - Birresborn
            template = Topology.directRides[11].clone();
            template.setStarttime((long) 0);
            addThreeEntries(template, daystart);

            // Gerolstein Mürlenbach
            start = (long)0;
            template = new Ride("Gerolstein", "Mürlenbach", start);
            addThreeEntries(template, daystart);

            // Gerolstein Salm
            template = Topology.directRides[8].clone();
            template.setStarttime((long) 0);
            addThreeEntries(template, daystart);

            // Kopp - Birresborn
            template = Topology.directRides[2].clone();
            template.setStarttime((long) 15*3600);
            addThreeEntries(template, daystart);

            // Kopp - Mürlenbach
            start = (long) 15*3600;
            template = new Ride("Kopp", "Mürlenbach", start);
            addThreeEntries(template, daystart);

            // Kopp - Salm
            start = (long) 15*3600;
            template = new Ride("Kopp", "Salm", start);
            addThreeEntries(template, daystart);

            // Kopp - Gerolstein
            start = (long) 15*3600;
            template = new Ride("Kopp", "Gerolstein", start);

            // Birresborn - Mürlenbach
            template = Topology.directRides[4].clone();
            template.setStarttime((long) 20*3600);
            addThreeEntries(template, daystart);

            // Birresborn - Salm
            start = (long) 20*3600;
            template = new Ride("Birresborn", "Salm", start);
            addThreeEntries(template, daystart);

            // Birresborn - Gerolstein
            start = (long) 20*3600;
            template = new Ride("Birresborn", "Gerolstein", start);
            addThreeEntries(template, daystart);

            // Mürlenbach - Salm
            template = Topology.directRides[6].clone();
            template.setStarttime((long) 25*3600);
            addThreeEntries(template, daystart);

            // Mürlenbach - Gerolstein
            start = (long) 25*3600;
            template = new Ride("Mürlenbach", "Gerolstein", start);
            addThreeEntries(template, daystart);

            // Salm - Gerolstein
            template = Topology.directRides[9].clone();
            template.setStarttime((long) 35*3600);
            addThreeEntries(template, daystart);
        }
    }

    private void addThreeEntries (Ride template, long daystart)
    {
        long[] dalytimes = {7 * 3600, 14 * 3600, 18 * 3600}; // 9h, 14h, 18h

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
    public ArrayList<Ride> searchUser (String name)
    {
        ArrayList<Ride> toReturn = new ArrayList<>();
        for (Ride ride: rides)
        {
            if (ride.getGuests().contains(name)) {
                ride.currentRequestUser = name;
                toReturn.add(ride);
            }
        }
        return toReturn;
    }

    public ArrayList<Ride> searchSharer (String name)
    {
        ArrayList<Ride> toReturn = new ArrayList<>();
        for (Ride ride: rides)
        {
            if (ride.getDriver() != null)
                if (ride.getDriver().equals(name)) {
                    ride.currentRequestUser = name;
                    toReturn.add(ride);
                }
        }
        return toReturn;
    }

    public void addRide (Ride ride)
    {
        rides.add(ride);
        Collections.sort(rides);
    }
}
