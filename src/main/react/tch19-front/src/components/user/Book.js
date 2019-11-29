import React, {useState, useEffect} from 'react'
import moment from "moment";


const Book = ({close, tour}) => {

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


    /*************
     *
     *  RENDERING
     *
     *************/


    return (
        <div className="w3-modal" style={{display: "block"}}>
            <div className="w3-modal-content animate-top" style={{width: "60%"}}>
                <div className="w3-container">
                    <span onClick={close} className="w3-button w3-display-topright">&times;</span>
                    <p>Fahrt {tour && "um " + moment(tour.starttime).format("HH:mm")} Buchen?</p>
                    <p>
                        <button
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