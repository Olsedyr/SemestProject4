package com.example.product.repository;

import com.example.product.model.Batch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Long> {

    List<Batch> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
