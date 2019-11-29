package com.triercityhack19.routing;

import com.triercityhack19.logic.Day;
import com.triercityhack19.logic.Ride;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/user/find", produces = "application/json")
    public ArrayList<Ride> getRidesForUser(@RequestBody Ride ride)
    {
        return today.search(ride);
    }

    @PostMapping(value = "/sharer/find", produces = "application/json")
    public ArrayList<Ride> getRidesForSharer(@RequestBody Ride ride)
    {
        return today.search(ride);
    }

    @PostMapping(value = "/user/new")
    public void newRideRequest (@RequestBody Ride ride)
    {
        ride.setOpen(true);
        today.addRide(ride);
    }

    @PostMapping(value = "/sharer/new")
    public void newRideOffer (@RequestBody Ride ride)
    {
        ride.setOpen(false);
        today.addRide(ride);
    }

    @PostMapping(value = "/user/book")
    public void newBooking (@RequestBody Ride ride)
    {
        /*
            Wenn wir sitze zählen müssen wir hier die Zahl der verfügbaren Sitze dekrementieren.
            Sonst müssen wir uns erstmal um nichts kümmern.
         */
        today.search(ride.getId());
    }

    @PostMapping(value = "/sharer/close")
    public void closeBooking (@RequestBody Ride ride)
    {
        today.search(ride.getId()).setOpen(false);
    }
}
