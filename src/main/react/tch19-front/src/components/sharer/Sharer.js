import React, {useState, useEffect} from 'react'
import cl from "classnames";
import DatePicker from "react-datepicker";
import DestinationPicker from "./DestinationPicker";
import moment from "moment"
import Book from "./Book";
import axios from "axios";
import New from "./New";


const Sharer = ({}) => {

    /*************
     *
     *  HOOKS
     *
     *************/

    const [showBook, setShowBook] = useState(false);
    const [tour, setTour] = useState(null);

    const [showNew, setShowNew] = useState(false);
    const [newTour, setNewTour] = useState(null);
    const [startDate, setStartDate] = useState(new Date());
    const [tours, setTours] = useState([]);

    const [start, setStart] = useState(null);
    const [destination, setDestination] = useState(null);

    const [nearest, setNearest] = useState(null);

    /*************
     *
     *  FUNCTIONS
     *
     *************/

    const update = () => {
        loadTours(startDate);
    };
    const find = (date) => {

        if (!start || !destination) {
            return;
        }
        const data = {
          start: start.value,
          dest: destination.value,
          starttime: moment(date).valueOf()/1000
        };

        axios.post("/api/sharer/find", data)
          .then(res => {
              setTours(res.data);
              calculateNextTimeslot(res.data, date)
          })
          .catch(err => console.log(err))
};

    const loadTours = (date) => {
        setStartDate(date);
        find(date);
    };

    const getDistanceRight = (time) => {
        const minutes = rightMoment.diff(moment.unix(time), "minutes");
        return `${(minutes/120) * 100 -5}%`;
    };



    const calculateNextTimeslot = (tours, startDate) => {
        setNearest(null);
        if (!tours || !tours.length) return;
        const starttime = moment(startDate).valueOf() / 1000;

        let nearest = null;
        tours.forEach(tour => {
            if (!nearest)
                nearest = tour;
            else if(Math.abs(starttime - nearest.time) < Math.abs((starttime - tour.starttime))){
                nearest = tour;
            }
        });

        if (!nearest) return;

        if (Math.abs(moment.unix(nearest.starttime).diff(moment(startDate), "minutes")) < 20)  {
            setNearest({...nearest, cost: "veryhigh"})
        } else if (Math.abs(moment.unix(nearest.starttime).diff(moment(startDate), "minutes")) < 40)  {
            setNearest({...nearest, cost: "high"})
        } else if (Math.abs(moment.unix(nearest.starttime).diff(moment(startDate), "minutes")) < 60)  {
            setNearest({...nearest, cost: "mediumhigh"})
        } else {
            setNearest(null);
        }

    };

    /*************
     *
     *  RENDERING
     *
     *************/

    const timelineStyle = {
        width: "100%",
        height: "100px",
    };

    const leftTime = moment(startDate).subtract(1, "hours").format("HH:mm");
    const rightTime = moment(startDate).add(1, "hours").format("HH:mm");

    const leftMoment = moment(startDate).subtract(1, "hours");
    const rightMoment = moment(startDate).add(1, "hours");

    const startTime = moment(startDate).format("HH:mm");

    return (
        <div style={{height:"100%"}} className={"animate-right"}>
            <div style={{height:"33%"}} className={"w3-blue"}>
                <div className={"w3-margin-top"}>
                    <DestinationPicker
                        start={start}
                        destination={destination}
                        setDestination={setDestination}
                        setStart={setStart}
                    />
                </div>
                <div className={"w3-center w3-margin-top"}>
                    <DatePicker
                        selected={startDate}
                        onChange={date => loadTours(date)}
                        showTimeSelect
                        timeFormat="HH:mm"
                        timeIntervals={5}
                        timeCaption="Zeit"
                        dateFormat="MMMM d, yyyy HH:mm"
                        todayButton="Heute"
                    />
                </div>
            </div>

            <div style={{height:"33%"}} className={"w3-green"}>
                <div className={"w3-container"}>
                    <div className={"w3-center"}>
                        <h3>Fahrten übernehmen</h3>
                    </div>
                    {tours.length ?
                        <div style={timelineStyle} className={"w3-display-container"}>
                            <div className={"w3-display-middle"} style={{width: "90%"}}>
                                <hr style={{backgroundColor: "black", height: 1}}/>
                                {tours.map(tour =>
                                    <div
                                        className={"button timelineButton w3-animate-opacity"}
                                        style={{right: getDistanceRight(tour.starttime), position: "absolute", bottom: 0}}
                                        onClick={() => {
                                            if(tour.isDriver) return;
                                            setTour(tour);
                                            setShowBook(true);
                                        }}>
                                        <div className={cl("w3-card", {"w3-light-green": tour.isDriver}, {"w3-blue": !tour.isDriver})} style={{padding: 4}}>
                                            Fahrt
                                        </div>
                                        <div className={"w3-small"}>{moment.unix(tour.starttime).format("HH:mm")}</div>
                                    </div>
                                )}
                            </div>
                            <div className={"w3-display-bottomleft w3-tiny"}>{leftTime}</div>
                            <div className={"w3-display-bottommiddle"}>
                                {startTime}
                            </div>
                            <div className={"w3-display-bottomright w3-tiny"}>{rightTime}</div>
                        </div>
                        :
                        <div className={"w3-center"}>
                            {!start || !destination ? "Bitte Start und Ziel auswählen": "Noch keine Fahrt in dieser Zeit"}
                        </div>
                    }
                </div>
            </div>

            <div style={{height:"33%"}}>
                <div className={"w3-center"}>
                    <h3>Neu</h3>
                    {!start || !destination ?
                        "Bitte Start und Ziel auswählen":
                        <div>
                            <div
                                onClick={() => {
                                    setNewTour({
                                        starttime : Math.floor(moment(startDate).valueOf()/1000),
                                        start: start.value,
                                        destination: destination.value
                                    });
                                    setShowNew(true);


                                }}
                                className={
                                    cl("w3-btn w3-blue",
                                        {"w3-orange": nearest && nearest.cost === "high"},
                                        {"w3-yellow": nearest && nearest.cost === "mediumhigh"},
                                        {"w3-red": nearest && nearest.cost === "veryhigh"},
                                        {"w3-blue": !nearest})
                                }>
                                neue Fahrt um {startTime}
                            </div>
                            {nearest &&
                                <div className={"w3-margin-top"}>
                                    <div>
                                        Hier fahren wahrscheinlich {nearest.cost === "veryhigh" ? "keine " : nearest.cost === "mediumhigh" ? "wenige " : nearest.cost === "high" && "sehr wenige "}Leute mit.
                                    </div>
                                    <div>
                                        Übernimm doch die Fahrt um &nbsp;
                                        <div
                                            className={"w3-btn w3-blue"}
                                            onClick={() => {
                                                setTour(nearest);
                                                setShowBook(true);
                                            }}>
                                            {moment.unix(nearest.starttime).format("HH:mm")}
                                        </div>

                                    </div>
                                </div>
                            }
                        </div>
                    }
                </div>
            </div>
            {showBook &&
                <Book
                    tour={tour}
                    close={() => setShowBook(false)}
                    update={update}
                />
            }
            {showNew &&
            <New
                update={update}
                tour={newTour}
                close={() => setShowNew(false)}
            />
            }
        </div>
    )

};

export default Sharer;