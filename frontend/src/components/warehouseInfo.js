import React, { useState } from 'react';

const WarehouseInfo = () => {
    const [status, setStatus] = useState("Idle"); // placeholder


    return (
        <div className="container">
            <div className="assembly-info">
                <h2>Warehouse info: </h2>
                <div className="battery-container">
                    <div className="status-text">Warehouse status: {status}</div>
                </div>
            </div>
        </div>
    );
};

export default WarehouseInfo;
