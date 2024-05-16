import React, { useState, useEffect } from 'react';
import axios from "axios";

const PrevProd = () => {
    // Initialize state with an empty array
    const [previousProductions, setPreviousProductions] = useState([]);
    const [selectedId, setSelectedId] = useState(null);

    // Function to toggle the selected production ID
    const toggleSelectedId = (id) => {
        setSelectedId(selectedId === id ? null : id);
    };

    // Fetch data from the backend when the component mounts
    useEffect(() => {
        axios.get('http://localhost:8080/api/production/previousProductions')
            .then(response => {
                // Check if the response data is an array
                if (Array.isArray(response.data)) {
                    setPreviousProductions(response.data);
                } else {
                    console.error('Response data is not an array:', response.data);
                }
            })
            .catch(error => {
                console.error('Error fetching previous productions:', error);
            });
    }, []);

    return (
        <div className="container">
            <div className="prev-production">
                <h2>Previous 10 Productions</h2>
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
                                <td>{new Date(prod.date).toLocaleDateString()}</td>
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
