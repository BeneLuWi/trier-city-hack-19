import React, {useState, useEffect} from 'react'
import moment from "moment";
import axios from "axios";

const New = ({tour, close, update}) => {

    /*************
     *
     *  HOOKS
     *
     *************/


    /*************
     *
     *  FUNCTIONS
     *
     *************/

    const newTour = () => {


        axios.post("/api/user/new", {...tour, dest: tour.destination})
            .then(() => {
                update();
                close();
            })
            .catch(err => console.log(err))
    };


    /*************
     *
     *  RENDERING
     *
     *************/


    return (
        <div className="w3-modal" style={{display: "block"}}>
            <div className="w3-modal-content animate-top" style={{width: "80%"}}>
                <div className="w3-container">
                    <span onClick={close} className="w3-button w3-display-topright">&times;</span>
                    <p>Neue Fahrt {tour && "um " + moment.unix(tour.starttime).format("HH:mm")} Buchen?</p>
                    <p>
                        <button
                            onClick={newTour}
                            className={"w3-btn w3-blue"}>
                            Ja
                        </button>
                        <button
                            className={"w3-margin-left w3-btn w3-red"}
                            onClick={close}>
                            Nein, abbrechen
                        </button>
                    </p>
                </div>
            </div>
        </div>
    )

};

export default New