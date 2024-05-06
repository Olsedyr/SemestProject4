package com.example.product.repository;

import com.example.product.model.Batch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatchRepository extends JpaRepository<Batch,Long> {

}
