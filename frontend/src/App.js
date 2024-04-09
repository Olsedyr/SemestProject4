import React, { useState, useEffect, useRef } from 'react';
import './App.css';
import agvInfo from './components/agvInfo';
import Inventory from './components/inventory';
import PrevProd from './components/PrevProd';
import ProdControl from './components/prodControl';
import ProdPdf from './components/prodPdf';
import AgvInfo from "./components/agvInfo";
import AssemblyInfo from "./components/assemblyInfo";
import WarehouseInfo from "./components/warehouseInfo";




const App = () => {

    // Return JSX
    return (
        <div className="container">
            <div className="header">
                <h1>Skateboards Production Line</h1>
            </div>
            <div className="dashboard">

                <AgvInfo />

                <AssemblyInfo />

                <WarehouseInfo />

                <ProdControl />

                <ProdPdf />

                <Inventory />

                <PrevProd />


            </div>

        </div>
    );
};

export default App;