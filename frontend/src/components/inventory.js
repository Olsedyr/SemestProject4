import React from 'react';

const Inventory = () => {
    // placeholder
    const inventoryItems = [
        { TrayId: 1, name: "Item 1", Timestamp: "12:00"},
        { TrayId: 2, name: "Item 2", Timestamp: "12:05"},
        { TrayId: 3, name: "Item 3", Timestamp: "12:10"},

    ];

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
                            <td>{item.TrayId}</td>
                            <td>{item.name}</td>
                            <td>{item.Timestamp}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Inventory;
