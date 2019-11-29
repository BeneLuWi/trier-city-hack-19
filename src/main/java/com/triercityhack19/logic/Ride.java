package com.triercityhack19.logic;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import static java.util.UUID.randomUUID;

public class Ride implements Comparable<Ride>
{
    public Ride(String start, String dest, Date starttime) {
        this.id = randomUUID().toString();
        this.start = start;
        this.dest = dest;
        this.starttime = starttime;
    }

    private String id;
    private String start;
    private String dest;
    private Date starttime;
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

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public boolean isOpen() {
        return driver==null;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void addGuest(String name)
    {
        guests.add(name);
    }
}
