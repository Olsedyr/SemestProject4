import React from 'react';

const Inventory = () => {
    // placeholder
    const inventoryItems = [
        { id: 1, name: "Item 1", quantity: 10 },
        { id: 2, name: "Item 2", quantity: 20 },
        { id: 3, name: "Item 3", quantity: 15 },

    ];

    return (
        <div className="container">
            <div className="inventory">
                <h2>Inventory</h2>
                <table className="inventory-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                    </tr>
                    </thead>
                    <tbody>
                    {inventoryItems.map(item => (
                        <tr key={item.id}>
                            <td>{item.id}</td>
                            <td>{item.name}</td>
                            <td>{item.quantity}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Inventory;
