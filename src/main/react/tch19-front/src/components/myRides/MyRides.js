import React, {useState, useEffect} from 'react'
import axios from "axios";
import moment from "moment";

const MyRides = ({}) => {

    /*************
     *
     *  HOOKS
     *
     *************/

    const [tours, setTours] = useState([]);

    useEffect(() => {
        getTours()
    },[]);

    /*************
     *
     *  FUNCTIONS
     *
     *************/

    const getTours = () => {
        axios.get("/api/rides")
            .then(res => setTours(res.data))
            .catch(err => console.log(err))
    };

    /*************
     *
     *  RENDERING
     *
     *************/


    return (
        <div style={{height: "100%"}}>
            <div className={"w3-blue animate-left"} style={{height: "40%"}}>
                <h2>Gebuchte Fahrten</h2>
                <ul className={"w3-ul"}>
                    {tours.filter(t => t.isGuest).map(tour =>
                        <li key={tour.id}>
                            Von {tour.start} nach {tour.dest} um {moment.unix(tour.starttime).format("DD YY HH:mm")}
                        </li>
                    )}
                </ul>
            </div>
            <div className={"w3-green animate-right"} style={{height: "40%"}}>
                <h2>Angebotene Fahrten</h2>
                <ul className={"w3-ul"}>
                    {tours.filter(t => t.isDriver).map(tour =>
                        <li key={tour.id}>
                            Von {tour.start} nach {tour.dest} um {moment.unix(tour.starttime).format("DD YY HH:mm")}
                        </li>
                    )}
                </ul>
            </div>
        </div>
    )

};

export default MyRides