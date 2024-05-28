package com.example.warehouse.service;

import com.example.warehouse.warehouse.GetInventoryResponse;
import com.example.warehouse.warehouse.InsertItemResponse;
import com.example.warehouse.warehouse.PickItemResponse;

/**
 * Interface for warehouse operations.
 */
public interface IWarehouseService {

    /**
     * Retrieves inventory information.
     * Preconditions:
     * - The connection to the warehouse management system must be established.
     *
     * Postconditions:
     * - Returns a GetInventoryResponse containing detailed inventory data.
     *
     * @return The inventory response from the warehouse
     */
    GetInventoryResponse getInventory();

    /**
     * Picks an item from the warehouse based on a tray ID.
     * Preconditions:
     * - The trayId must be a valid identifier for a tray within the warehouse (1-10).
     * - The specified tray must contain an item.
     *
     * Postconditions:
     * - The item is removed from the tray.
     * - Returns a PickItemResponse indicating success or failure.
     *
     * @param trayId The tray ID from which the item should be picked
     * @return The response after picking an item
     */
    PickItemResponse pickItem(String trayId);

    /**
     * Inserts an item into the warehouse at a specified tray.
     * Preconditions:
     * - The trayId must correspond to a valid tray in the warehouse (1-10).
     * - A valid name must be provided.
     *
     * Postconditions:
     * - The item is added to the specified tray.
     * - Returns an InsertItemResponse indicating the success of the operation.
     *
     * @param trayId The tray ID where the item should be inserted
     * @param name The name of the item to insert
     * @return The response after inserting an item
     */
    InsertItemResponse insertItem(String trayId, String name);

    /**
     * Fills all trays with items, using the first 10 available parts in the database.
     * Preconditions:
     * - The database must Parts inserted into the Parts table
     *
     * Postconditions:
     * - Each tray in the warehouse is filled with items from the parts list.
     * - Operation should not affect items already present in the warehouse.
     */
    void fillAll();

    /**
     * Initiates the picking of items from all trays.
     *
     * Postcondition:
     * - All specified trays are cleared of items, or items are prepared for dispatch.
     */
    void pickAll();
}