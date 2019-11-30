package com.triercityhack19.routing;

import com.triercityhack19.logic.Day;
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
    Day today;

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
        ArrayList<Ride> result = today.search(ride);
        result.forEach(c -> c.currentRequestUser = username);
        return result;
    }

    @PostMapping(value = "/user/rides")
    public ArrayList<Ride> userRides ()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return today.searchUser(username);
    }

    @PostMapping(value = "/sharer/rides")
    public ArrayList<Ride> sharerRides ()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return today.searchSharer(username);
    }

    @PostMapping(value = "/user/new")
    public void newRideRequest (@RequestBody Ride ride)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ride.setDriver(null);
        ride.addGuest(username);
        today.addRide(ride);
    }

    @PostMapping(value = "/sharer/new")
    public void newRideOffer (@RequestBody Ride ride)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        ride.setDriver(username);
        today.addRide(ride);
    }

    @PostMapping(value = "/user/book")
    public void newBooking (@RequestBody Ride ride)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        today.search(ride.getId()).addGuest(username);
    }

    @PostMapping(value = "/sharer/close")
    public void closeBooking (@RequestBody Ride ride)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        today.search(ride.getId()).setDriver(username);
    }
}
