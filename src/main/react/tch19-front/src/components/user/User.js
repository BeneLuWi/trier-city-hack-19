import React, {useState, useEffect} from 'react'
import cl from "classnames";
import DatePicker from "react-datepicker";
import DestinationPicker from "./DestinationPicker";
import moment from "moment"
import Book from "./Book";
import axios from "axios";


const User = ({}) => {

    /*************
     *
     *  HOOKS
     *
     *************/

    const exampleTours = [
        {
            id: "0123",
            starttime: moment().subtract(20, "minutes")
        },
        {
            id: "1230123",
            starttime: moment().add(10, "minutes")
        }
    ];

    const [showBook, setShowBook] = useState(false);
    const [tour, setTour] = useState(null);

    const [showNew, setShowNew] = useState(false);
    const [startDate, setStartDate] = useState(new Date());
    const [tours, setTours] = useState([]);

    const [start, setStart] = useState(null);
    const [destination, setDestination] = useState(null);


    /*************
     *
     *  FUNCTIONS
     *
     *************/

    const find = (starttime) => {
          const data = {
              start: start.value,
              destination: destination.value,
              starttime: moment(starttime).valueOf()
          };

          axios.post("/api/user/find", data)
              .then(res => setTours(res.data))
              .catch(err => console.log(err))
    };

    const loadTours = (date) => {
        setStartDate(date);
        find(date);
    };

    const getDistanceRight = (time) => {
        const minutes = rightMoment.diff(moment(time), "minutes");
        return `${(minutes/120) * 100 -5}%`;
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
        <div style={{height:"99vh"}}>
            <div style={{height:"33%"}}>
                <DestinationPicker
                    start={start}
                    destination={destination}
                    setDestination={setDestination}
                    setStart={setStart}

                />
                <div className={"w3-center"}>
                    <DatePicker
                        selected={startDate}
                        onChange={date => loadTours(date)}
                        showTimeSelect
                        timeFormat="HH:mm"
                        timeIntervals={5}
                        timeCaption="Startzeit"
                        dateFormat="MMMM d, yyyy HH:mm"
                        todayButton="Heute"
                    />
                </div>
            </div>

            <div style={{height:"33%"}}>
                <div className={"w3-container"}>
                    <div className={"w3-center"}>
                        <h3>Mitfahren</h3>
                    </div>
                    {tours.length ?
                        <div style={timelineStyle} className={"w3-display-container"}>
                            <div className={"w3-display-middle"} style={{width: "90%"}}>
                                <hr style={{backgroundColor: "black", height: 1}}/>
                                {tours.map(tour =>
                                    <div
                                        className={"button timelineButton"} style={{right: getDistanceRight(tour.starttime), position: "absolute", bottom: 0}}
                                        onClick={() => {
                                            setTour(tour);
                                            setShowBook(true);
                                        }}>
                                        <div className={"w3-green w3-card"} style={{padding: 4}}>
                                            Fahrt
                                        </div>
                                        <div className={"w3-opacity w3-small"}>{moment(tour.starttime).format("HH:mm")}</div>
                                    </div>
                                )}
                            </div>
                            <div className={"w3-display-bottomleft w3-tiny"}>{leftTime}</div>
                            <div className={"w3-display-bottommiddle"}>{startTime}</div>
                            <div className={"w3-display-bottomright w3-tiny"}>{rightTime}</div>
                        </div>
                        : <div>Zeitpunkt und weg wählen</div>
                    }
                </div>
            </div>

            <div style={{height:"33%"}}>
                <div className={"w3-center"}>
                    <h3>Neu</h3>
                    <div className={"w3-btn w3-blue"}>neue Fahrt um {startTime}</div>
                </div>
            </div>
            {showBook &&
                <Book
                    tour={tour}
                    close={() => setShowBook(false)}
                />
            }
        </div>
    )

};

export default User