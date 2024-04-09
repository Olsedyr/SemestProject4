import React, { useState } from 'react';

const WarehouseInfo = () => {
    const [status, setStatus] = useState("Idle"); // placeholder
    const [program, setProgram] = useState("Not running any program"); // placeholder


    return (
        <div className="container">
            <div className="assembly-info">
                <h2>Warehouse info: </h2>
                <div className="battery-container">
                    <div className="status-text">Warehouse status: {status}</div>
                    <br/>
                    <div className="status-text">Currently running: {program}</div>

                </div>
            </div>
        </div>
    );
};

export default WarehouseInfo;
