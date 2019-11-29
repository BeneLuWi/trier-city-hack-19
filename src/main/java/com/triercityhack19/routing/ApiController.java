package com.triercityhack19.routing;

import com.triercityhack19.logic.Day;
import com.triercityhack19.logic.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    Day today;

    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    @GetMapping(value = "/user/find", produces = "application/json")
    public ArrayList<Ride> getRidesForUser(@RequestBody Ride ride)
    {
        return today.search(ride);
    }

    @GetMapping(value = "/sharer/find", produces = "application/json")
    public ArrayList<Ride> getRidesForSharer(@RequestBody Ride ride)
    {
        return today.search(ride);
    }

    @PostMapping(value = "/user/new")
    public void newRideRequest (@RequestBody Ride ride)
    {
        ride.setDriver(null);
        ride.addGuest(username);
        today.addRide(ride);
    }

    @PostMapping(value = "/sharer/new")
    public void newRideOffer (@RequestBody Ride ride)
    {
        ride.setDriver(username);
        today.addRide(ride);
    }

    @PostMapping(value = "/user/book")
    public void newBooking (@RequestBody Ride ride)
    {
        today.search(ride.getId()).addGuest(username);
    }

    @PostMapping(value = "/sharer/close")
    public void closeBooking (@RequestBody Ride ride)
    {
        today.search(ride.getId()).setDriver(username);
    }
}
