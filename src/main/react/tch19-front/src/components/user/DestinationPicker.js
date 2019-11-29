import React, {useState, useEffect} from 'react'
import Select from 'react-select'

const options = [
    { value: 'Gerolstein', label: 'Gerolstein' },
    { value: 'Kopp', label: 'Kopp' },
    { value: 'Birresborn', label: 'Birresborn' },
    { value: 'Mürlenbach', label: 'Mürlenbach' },
    { value: 'Salm', label: 'Salm' }
];

const DestinationPicker = ({setStart, start, destination, setDestination}) => {

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
        <div className="w3-row">
            <div className="w3-col s5">
                <Select
                    value={start}
                    onChange={setStart}
                    options={options}
                />
            </div>
            <div className="w3-col s2 w3-center" style={{paddingTop: 10}}>
                <div>nach</div>
            </div>
            <div className="w3-col s5 ">
                <Select
                    value={destination}
                    onChange={setDestination}
                    options={options}
                />
            </div>
        </div>
    )

};

export default DestinationPicker