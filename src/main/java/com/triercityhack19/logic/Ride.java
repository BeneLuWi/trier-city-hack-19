package com.triercityhack19.logic;


import java.time.LocalDateTime;
import java.util.Date;

import static java.util.UUID.randomUUID;

public class Ride implements Comparable<Ride>
{
    public Ride(String start, String dest, long starttime, boolean open) {
        this.id = randomUUID().toString();
        this.start = start;
        this.dest = dest;
        this.starttime = starttime;
        this.open = open;
    }

    private String id;
    private String start;
    private String dest;
    private Long starttime;
    private boolean open;

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
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
