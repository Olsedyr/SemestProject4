import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AgvInfo = () => {
    const [batteryLife, setBatteryLife] = useState(0);
    const [status, setStatus] = useState("");
    const [program, setProgram] = useState("");
    const [timestamp, setTimestamp] = useState("");

    useEffect(() => {
        const intervalId = setInterval(fetchAgvStatus, 1000); // Fetch AGV status every 1 second

        return () => clearInterval(intervalId);
    }, []);

    // Fetch AGV status by, by making a GET request to the endpoint
    const fetchAgvStatus = () => { 
        axios.get('http://localhost:8080/agv/status') 
            .then(response => {
                const data = response.data;
                // Update states with response data
                setBatteryLife(data.battery); 
                setStatus(
                    data.state === 1 ? "Idle" : 
                    data.state === 2 ? "Executing" :
                    data.state === 3 ? "Charging" :
                    "Unknown"
                );
                setProgram(data['program name']);
                setTimestamp(data.timestamp);
            })
            .catch(error => {
                console.error('Error fetching AGV status:', error);
            });
    };

    // Function to control AGV program
    const controlAgvProgram = (programName) => {
        axios.put(`http://localhost:8080/agv/program/${programName}`)
            .then(response => {
                console.log(`AGV program set to ${programName}`);
            })
            .catch(error => {
                console.error(`Error setting AGV program to ${programName}:`, error);
            });
    };

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
                    <br/>
                    <div className="timestamp-text">Timestamp: {timestamp}</div>
                    <br/>
                    {/* Buttons to control AGV program */}
                    <button className="button" onClick={() => controlAgvProgram('move-to-charger')}>Move to Charger</button> 
                    <button className="button" onClick={() => controlAgvProgram('move-to-assembly')}>Move to Assembly Station</button> 
                    <button className="button" onClick={() => controlAgvProgram('move-to-storage')}>Move to Warehouse</button> 
                    <br/>
                    <button className="button" onClick={() => controlAgvProgram('put-assembly')}>Put Assembly</button> 
                    <button className="button" onClick={() => controlAgvProgram('pick-assembly')}>Pick Assembly</button> 
                    <button className="button" onClick={() => controlAgvProgram('pick-warehouse')}>Pick Warehouse</button> 
                    <button className="button" onClick={() => controlAgvProgram('put-warehouse')}>Put Warehouse</button>
                </div>
            </div>
        </div>
    );
};

export default AgvInfo;
