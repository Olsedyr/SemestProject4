package com.example.product.repository;

import com.example.product.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface PartRepository extends JpaRepository<Part,Long> {
    Optional<Part> findByName(String name);

}
