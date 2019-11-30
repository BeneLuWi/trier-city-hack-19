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
        <div className="w3-padding">
            <div className="w3-text-black place-select">
                <Select
                    value={start}
                    onChange={setStart}
                    options={options}
                />
            </div>
            <div className="w3-center">
                <div>nach</div>
            </div>
            <div className="w3-text-black place-select">
                <Select
                    value={destination}
                    onChange={setDestination}
                    options={options}
                />
            </div>
            <div className="w3-center">
                <div>um</div>
            </div>
        </div>
    )

};

export default DestinationPicker