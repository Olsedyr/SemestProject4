package com.example.demo.products.repository;

import com.example.demo.products.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch,Long> {
}
