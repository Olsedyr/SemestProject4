import React, { useState } from 'react';
import axios from 'axios';

const ProdControl = () => {
    // State variables to keep track of selected product and quantity
    const [selectedProduct, setSelectedProduct] = useState(null);
    const [quantity, setQuantity] = useState(1);
    const [program, setProgram] = useState("");

    // Function to handle product selection
    const handleProductSelection = (product) => {
        setSelectedProduct(product);
    };

    // Function to handle quantity change
    const handleQuantityChange = (event) => {
        setQuantity(parseInt(event.target.value));
    };

    // Function to control AGV program
    const controlAgvProgram = (programName) => {
        axios.put(`http://localhost:8080/agv/program/${programName}`)
            .then(response => {
                console.log(`AGV program set to ${programName}`);
            })
            .catch(error => {
                console.error(`Error setting AGV program to ${programName}:`, error);
            });
    };

    const handleStartProduction = () => {
        // Start production logic here
        console.log(`Starting production of ${quantity} ${selectedProduct.name}(s)`);

        // Move AGV to warehouse
        controlAgvProgram('move-to-storage');
    };

    // Product data
    const products = [
        { id: 1, name: "Small skateboard", description: "A small-sized skateboard suitable for children"},
        { id: 2, name: "Medium skateboad", description: "A medium-sized skateboard"},
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

                            <label htmlFor="quantity">Quantity:</label>
                            <input
                                type="number"
                                id="quantity"
                                name="quantity"
                                min="1"
                                value={quantity}
                                onChange={handleQuantityChange}
                            />
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
