import React, { useState } from 'react';
import axios from 'axios';

const ProdControl = () => {
    // State variables to keep track of selected product and quantity
    const [selectedProduct, setSelectedProduct] = useState(null);

    // Function to handle product selection
    const handleProductSelection = (product) => {
        setSelectedProduct(product);
    };


    // Function to control AGV program
    const controlProduction = () => {
        axios.post(`http://localhost:8080/api/production/start?name=${selectedProduct.name}`)
            .then(response => {
                console.log(`Producing ${selectedProduct.name}`);
            })
            .catch(error => {
                console.error(`Error producing ${selectedProduct.name}:`, error);
            });
    };

    const handleStartProduction = () => {
        // Start production logic here
        console.log(`Starting production of ${selectedProduct.name}`);

        // Move AGV to warehouse
        controlProduction();
    };

    // Product data
    const products = [
        { id: 1, name: "Small skateboard", description: "A small-sized skateboard suitable for children"},
        { id: 2, name: "Medium skateboard", description: "A medium-sized skateboard"},
        { id: 3, name: "Large skateboard", description: "A large-sized skateboard"}
    ];
    

    return (
        <div className="container">
            <div className="assembly-info">
                <h2>Product Catalog</h2>
                <div className="product-catalog">
                    <h3>Products</h3>
                    <ul>
                        {products.map(product => (
                            <li key={product.id} onClick={() => handleProductSelection(product)}>
                                {product.name} - {product.description}
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="selected-product">
                    <h3>Choosen product</h3>
                    {selectedProduct ? (
                        <div>
                            <p>{selectedProduct.description}</p>

                            <br></br>
                            <br></br>
                            <button className="button" onClick={handleStartProduction}>Start Production</button>
                        </div>
                    ) : (
                        <br></br>
                    )}
                </div>
            </div>
        </div>
    );
};

export default ProdControl;
