package com.example.warehouse.repository;

import com.example.warehouse.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // You can define custom query methods here if needed
}
