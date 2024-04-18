package com.example.demo.service;

import com.example.demo.model.testDB;
import com.example.demo.repository.TestDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TestDBService {

    private final TestDBRepository testDBRepository;

    @Autowired
    public TestDBService(TestDBRepository testDBRepository) {
        this.testDBRepository = testDBRepository;
    }

    @Transactional
    public void createTestData() {
        testDB testData = new testDB();
        testData.setQuantity(10);
        testData.setDescription("Wheels");
        testDBRepository.save(testData);
    }
}
