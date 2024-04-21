package com.example.demo.products.repository;

import com.example.demo.products.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComponentRepository extends JpaRepository<Component,Long> {
    Optional<Component> findByName(String name);
}

