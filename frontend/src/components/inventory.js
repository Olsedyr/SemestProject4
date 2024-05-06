import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Inventory = () => {
    const [inventoryItems, setInventoryItems] = useState([]);

    useEffect(() => {
        fetchInventoryItems();
    }, []);

    const fetchInventoryItems = async () => {
        try {
            const response = await axios.get('/api/getInventory');
            setInventoryItems(response.data);
        } catch (error) {
            console.error('Error fetching inventory items:', error);
        }
    };

    return (
        <div className="container">
            <div className="inventory">
                <h2>Inventory</h2>
                <table className="inventory-table">
                    <thead>
                    <tr>
                        <th>Tray-ID</th>
                        <th>Name</th>
                        <th>TimeStamp</th>
                    </tr>
                    </thead>
                    <tbody>
                    {inventoryItems.map(item => (
                        <tr key={item.id}>
                            <td>{item.trayId}</td>
                            <td>{item.name}</td>
                            <td>{item.timestamp}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Inventory;
