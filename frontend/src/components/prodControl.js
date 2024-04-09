import React, { useState, useEffect } from 'react';



const prodControl = () => {

    // Define click handlers for each button if needed

    const handleStartProduction = () => {
        // Implement logic for starting production
    };

    const handleStopProduction = () => {
        // Implement logic for stopping production
    };


    const handleAbort = () => {
        // Implement logic for aborting
    };

    return (
        <div className="controls">
            <button className="button" onClick={handleStartProduction}>Start Production</button>
            <button className="button" onClick={handleStopProduction}>Stop Production</button>
            <button className="button" onClick={handleAbort}>Abort</button>
        </div>
    );
};

export default prodControl;