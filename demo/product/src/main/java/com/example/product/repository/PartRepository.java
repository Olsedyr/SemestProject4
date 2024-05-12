package com.example.product.repository;

import com.example.product.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    Optional<Part> findByName(String name);

    @Query("SELECT p.trayId FROM Part p WHERE p.name = :partName")
    int findTrayIdByPartName(String partName);
}

