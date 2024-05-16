import React, { useState, useEffect } from 'react';
import axios from 'axios';

const PrevProd = () => {
    const [previousProductions, setPreviousProductions] = useState([]);
    const [selectedId, setSelectedId] = useState(null);

    const toggleSelectedId = (id) => {
        setSelectedId(selectedId === id ? null : id);
    };

    useEffect(() => {
        axios.get('http://localhost:8080/api/production/previousProductions')
            .then(response => {
                console.log('Response data:', response.data);  // Log the response data
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

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        if (isNaN(date)) return 'Invalid Date';

        const options = {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        };

        const formattedDate = date.toLocaleDateString(undefined, options);

        return `${formattedDate}`;
    };

    return (
        <div className="container">
            <div className="prev-production">
                <h2>Previous 10 Productions</h2>
                <table className="prev-production-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Completed</th>
                        <th>Created At</th>
                    </tr>
                    </thead>
                    <tbody>
                    {previousProductions.map(batch => (
                        <React.Fragment key={batch.id}>
                            <tr onClick={() => toggleSelectedId(batch.id)}>
                                <td>{batch.id}</td>
                                <td>{batch.completed ? 'Yes' : 'No'}</td>
                                <td>{formatDate(batch.createdAt)}</td>
                            </tr>
                            {selectedId === batch.id && (
                                <tr>
                                    <td colSpan="3">{batch.log}</td>
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
