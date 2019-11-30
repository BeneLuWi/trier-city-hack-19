package com.triercityhack19.logic;


import java.util.ArrayList;

import static java.util.UUID.randomUUID;

public class Ride implements Comparable<Ride>
{
    public Ride(String start, String dest, Long starttime, String driver) {
        this.id = randomUUID().toString();
        this.start = start;
        this.dest = dest;
        this.starttime = starttime;
        this.driver = driver;
    }

    public Ride(String start, String dest, Long starttime) {
        this.id = randomUUID().toString();
        this.start = start;
        this.dest = dest;
        this.starttime = starttime;
        this.driver = null;
    }

    public Ride()
    {
        this.id = randomUUID().toString();
    }

    private String id;
    private String start;
    private String dest;
    private Long starttime;
    private ArrayList<String> guests = new ArrayList<>();
    private String driver = null;

    @Override
    public int compareTo(Ride o) {
        return start.compareTo(o.start);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public boolean isOpen() {
        return driver==null;
    }

    public String getDriver()
    {
        return this.driver;
    }

    public ArrayList<String> getGuests()
    {
        return this.guests;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void addGuest(String name)
    {
        guests.add(name);
    }


    public String currentRequestUser = "";
    public boolean isisGuest()
    {
        return guests.contains(currentRequestUser);
    }
    public boolean isisDriver()
    {
        return driver==null? false : driver.equals(currentRequestUser);
    }

    @Override
    public Ride clone()
    {
        Ride toReturn = new Ride(this.start, this.dest, this.starttime, this.driver);
        toReturn.setId(randomUUID().toString());
        return toReturn;
    }

    // Zwei Fahrten sind gleich, wenn sie den gleichen Start und das Gleiche Ziel haben

    @Override
    public int hashCode ()
    {
        return start.hashCode() + dest.hashCode();
    }

    /*
    public boolean equals(Ride ride)
    {
        return this.hashCode()==ride.hashCode();
    }
     */
}
