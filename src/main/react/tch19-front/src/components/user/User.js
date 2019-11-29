import React, {useState, useEffect} from 'react'
import cl from "classnames";
import DatePicker from "react-datepicker";
import DestinationPicker from "./DestinationPicker";
import moment from "moment"
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

    const [startDate, setStartDate] = useState(new Date());
    const [tours, setTours] = useState(exampleTours);
    /*************
     *
     *  FUNCTIONS
     *
     *************/


    const getDistanceRight = (time) => {
        const minutes = rightMoment.diff(moment(time), "minutes");

        return `${(minutes/120) * 100}%`;

    };

    /*************
     *
     *  RENDERING
     *
     *************/

    const timelineStyle = {
        width: "100%",
        height: "100px",
        borderLeft: "1px solid black",
        borderRight: "1px solid black",
    };

    const leftTime = moment(startDate).subtract(1, "hours").format("HH:mm");
    const rightTime = moment(startDate).add(1, "hours").format("HH:mm");

    const leftMoment = moment(startDate).subtract(1, "hours");
    const rightMoment = moment(startDate).add(1, "hours");

    return (
        <div>
            <DestinationPicker/>
            <div className={"w3-center"}>
                <DatePicker
                    selected={startDate}
                    onChange={date => setStartDate(date)}
                    showTimeSelect
                    timeFormat="HH:mm"
                    timeIntervals={5}
                    timeCaption="Startzeit"
                    dateFormat="MMMM d, yyyy h:mm aa"
                    todayButton="Heute"
                />
            </div>
            <div className={"w3-center"}>
                {moment(startDate).format("HH:mm")}
            </div>

            <div className={"w3-container w3-margin-top"}>
                <div style={timelineStyle} className={"w3-display-container"}>
                    <div className={"w3-display-middle"} style={{width: "95%"}}>
                        {tours.map(tour =>
                            <div className={"button"} style={{right: getDistanceRight(tour.starttime), position: "absolute"}}>
                                <div className={"w3-green w3-padding"}>
                                    Tour
                                </div>
                                <div className={"w3-opacity w3-small"}>{moment(tour.starttime).format("HH:mm")}</div>
                            </div>
                        )}
                    </div>

                    <div className={"w3-display-bottomleft w3-tiny"}>{leftTime}</div>
                    <div className={"w3-display-bottomright w3-tiny"}>{rightTime}</div>
                </div>
            </div>
        </div>
    )

};

export default User