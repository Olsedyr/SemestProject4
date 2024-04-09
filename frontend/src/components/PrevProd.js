import React, { useState } from 'react';

const PrevProd = () => {
    // placeholder data
    const previousProductions = [
        { id: 1, product: "Product A", quantity: 100, date: "2023-01-01", additionalInfo: "Additional info for Product A" },
        { id: 2, product: "Product B", quantity: 150, date: "2023-01-05", additionalInfo: "Additional info for Product B" },
        { id: 3, product: "Product C", quantity: 200, date: "2023-01-10", additionalInfo: "Additional info for Product C" },
    ];

    // keep track of the currently selected production ID
    const [selectedId, setSelectedId] = useState(null);

    // Function to toggle the selected production ID
    const toggleSelectedId = (id) => {
        setSelectedId(selectedId === id ? null : id);
    };

    return (
        <div className="container">
            <div className="prev-production">
                <h2>Previous Productions</h2>
                <table className="prev-production-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    {previousProductions.map(prod => (
                        <React.Fragment key={prod.id}>
                            <tr onClick={() => toggleSelectedId(prod.id)}>
                                <td>{prod.id}</td>
                                <td>{prod.product}</td>
                                <td>{prod.quantity}</td>
                                <td>{prod.date}</td>
                            </tr>
                            {selectedId === prod.id && (
                                <tr>
                                    <td colSpan="4">{prod.additionalInfo}</td>
                                </tr>
                            )}
                        </React.Fragment>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default PrevProd;
