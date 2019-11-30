import React, {useState, useEffect} from 'react'
import moment from "moment";

import axios from "axios";

const Book = ({close, tour, update}) => {

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
    const book = () => {
        axios.post("/api/sharer/close", {...tour, dest: tour.destination})
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
                    <p>Fahrt {tour && "um " + moment.unix(tour.starttime).format("HH:mm")} Übernehmen?</p>
                    <p>
                        <button
                            onClick={book}
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

export default Book