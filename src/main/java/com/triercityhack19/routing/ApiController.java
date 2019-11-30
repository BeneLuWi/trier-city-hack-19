package com.triercityhack19.routing;

import com.triercityhack19.logic.Schedule;
import com.triercityhack19.logic.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    Schedule schedule;

    @PostMapping(value = "/user/find", produces = "application/json")
    public ArrayList<Ride> getRidesForUser(@RequestBody Ride ride)
    {
        return getRides(ride);
    }

    @PostMapping(value = "/sharer/find", produces = "application/json")
    public ArrayList<Ride> getRidesForSharer(@RequestBody Ride ride)
    {
        return getRides(ride);
    }

    private ArrayList<Ride> getRides (Ride ride)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ArrayList<Ride> result = schedule.search(ride);
        result.forEach(c -> c.currentRequestUser = username);
        return result;
    }

    @PostMapping(value = "/user/rides")
    public ArrayList<Ride> userRides ()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return schedule.searchUser(username);
    }

    @PostMapping(value = "/sharer/rides")
    public ArrayList<Ride> sharerRides ()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return schedule.searchSharer(username);
    }

    @PostMapping(value = "/rides")
    public ArrayList<Ride> myRides ()
    {
        ArrayList<Ride> toReturn = new ArrayList<>();
        toReturn.addAll(sharerRides());
        toReturn.addAll(userRides());
        return toReturn;
    }

    @PostMapping(value = "/user/new")
    public void newRideRequest (@RequestBody Ride ride)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ride.setDriver(null);
        ride.addGuest(username);
        schedule.addRide(ride);
    }

    @PostMapping(value = "/sharer/new")
    public void newRideOffer (@RequestBody Ride ride)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ride.setDriver(username);
        schedule.addRide(ride);
    }

    @PostMapping(value = "/user/book")
    public void newBooking (@RequestBody Ride ride)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        schedule.search(ride.getId()).addGuest(username);
    }

    @PostMapping(value = "/sharer/close")
    public void closeBooking (@RequestBody Ride ride)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        schedule.search(ride.getId()).setDriver(username);
    }
}
