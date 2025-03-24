package com.example.demo.controller;

import com.example.demo.model.TestCase;
import com.example.demo.model.Priority;
import com.example.demo.model.Status;
import com.example.demo.services.TestCaseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestCaseService testCaseService;

    // ✅ Test creating a test case via API
    @Test
    public void testCreateTestCase() throws Exception {
        mockMvc.perform(post("/api/testcases")
                        .param("title", "Login Test")
                        .param("description", "Test login functionality")
                        .param("status", "PENDING")
                        .param("priority", "HIGH")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Login Test"));
    }

    // ✅ Test retrieving a non-existing test case (Expect 404)
    @Test
    public void testGetTestCaseById_NotFound() throws Exception {
        mockMvc.perform(get("/api/testcases/99"))
                .andExpect(status().isNotFound());
    }

    // ✅ Test updating a test case (Expect Success)
    @Test
    public void testUpdateTestCase() throws Exception {
        TestCase updatedTestCase = new TestCase("1", "Updated Test", "Updated description", Status.PASSED, Priority.MEDIUM, null, null);

        when(testCaseService.updateTestCase(Mockito.anyString(), any(TestCase.class))).thenReturn(updatedTestCase);

        mockMvc.perform(put("/api/testcases/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Test\",\"description\":\"Updated description\",\"status\":\"PASSED\",\"priority\":\"MEDIUM\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Test"));
    }
}
