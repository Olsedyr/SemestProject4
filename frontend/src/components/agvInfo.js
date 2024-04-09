import React, { useState } from 'react';

const AgvInfo = () => {
    const [batteryLife, setBatteryLife] = useState(80); // placeholder
    const [status, setStatus] = useState("Idle"); // placeholder
    const [program , setProgram] = useState("Program 1"); // placeholder

    return (
        <div className="container">
            <div className="agv-info">
                <h2>AGV info: </h2>
                <div className="battery-container">
                    <div className="battery-bar" style={{ width: `${batteryLife}%` }}></div>
                    <div className="battery-text">Automated Guided Vehicle Battery life: {batteryLife}%</div>
                    <br/>
                    <div className="status-text">AGV Status: {status}</div>
                    <br/>
                    <div className="program-text">Program: {program}</div>
                </div>
            </div>
        </div>
    );
};

export default AgvInfo;
