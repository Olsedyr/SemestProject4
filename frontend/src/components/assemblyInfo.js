import React, { useState } from 'react';

const AssemblyInfo = () => {

    const [status, setStatus] = useState("Idle"); // placeholder

    return (
        <div className="container">
            <div className="assembly-info">
                <h2>Assembly info: </h2>
                <div className="battery-container">
                    <div className="status-text">Assembly status: {status}</div>
                </div>
            </div>
        </div>
    );
};

export default AssemblyInfo;
