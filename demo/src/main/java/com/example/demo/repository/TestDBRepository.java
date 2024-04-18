package com.example.demo.repository;

import com.example.demo.model.testDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDBRepository extends JpaRepository<testDB, Integer> {
    // You can define additional custom query methods here if needed
}
