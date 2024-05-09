package com.example.product.service;

import com.example.product.model.Part;
import com.example.product.repository.PartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class PartServiceTest {

    @Mock
    private PartRepository partRepositoryMock;

    @InjectMocks
    private PartService partService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewPartWithNameAndDescription() {
        String name = "TestPart";
        String description = "Test Description";

        partService.addNewPart(name, description);

        // Verify that the save method is called with the correct arguments
        verify(partRepositoryMock).save(new Part(name, description));
    }

    @Test
    public void testAddNewPartWithName() {
        String name = "TestPart";

        partService.addNewPart(name);

        // Verify that the save method is called with the correct arguments
        verify(partRepositoryMock).save(new Part(name));
    }
}