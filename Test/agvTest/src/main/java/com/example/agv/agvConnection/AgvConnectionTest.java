package com.example.agv.agvConnection;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.RequestEntity.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
public class AgvConnectionTest {

    @MockBean
    private RestTemplate restTemplate;

    private AgvConnection agvConnection;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        agvConnection = AgvConnection.getInstance();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testSetProgram() throws Exception {
        // Mock the HTTP request and response
        mockServer.expect(requestTo("http://localhost:8082/v1/status/"))
                .andExpect(method(HttpMethod.PUT))
                .andExpect(content().json("{\"Program name\":\"PickWarehouseOperation\", \"state\":1}"))
                .andRespond(withSuccess());

        // Perform the action
        agvConnection.setProgram(AgvPrograms.PickWarehouseOperation);

        // Verify that the HTTP request was made
        mockServer.verify();

        // Add additional assertions if needed
    }

    @Test
    public void testStartProgram() throws Exception {
        // Mock the HTTP request and response
        mockServer.expect(requestTo("http://localhost:8082/v1/status/"))
                .andExpect(method(HttpMethod.PUT))
                .andExpect(content().json("{\"state\":2}"))
                .andRespond(withSuccess());

        // Perform the action
        agvConnection.startProgram();

        // Verify that the HTTP request was made
        mockServer.verify();

        // Add additional assertions if needed
    }

    @Test
    public void testGetAgvStatus() {
        // Mock the HTTP response
        mockServer.expect(requestTo("http://localhost:8082/v1/status/"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"battery\":100, \"program name\":\"TestProgram\", \"state\":1, \"timestamp\":\"2022-05-05\"}", MediaType.APPLICATION_JSON));

        // Perform the action
        AgvStatus status = agvConnection.getAgvStatus();

        // Verify the returned AgvStatus object
        assertEquals(100, status.getBattery());
        assertEquals("TestProgram", status.getProgramName());
        assertEquals(1, status.getState());
        assertEquals("2022-05-05", status.getTimestamp());

        // Verify that the HTTP request was made
        mockServer.verify();

        // Add additional assertions if needed
    }
}