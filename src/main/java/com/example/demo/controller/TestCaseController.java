package com.example.demo.controller;

import com.example.demo.model.TestCase;
import com.example.demo.model.Status;
import com.example.demo.model.Priority;
import com.example.demo.services.TestCaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Test Case Management", description = "CRUD operations for test cases")
@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {
    
    @Autowired
    private TestCaseService service;

    @Operation(summary = "Get all test cases", description = "Retrieve a paginated list of test case with optional filtering by status and priority")
    @GetMapping
    public ResponseEntity<Page<TestCase>> getAllTestCases(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllTestCases(status, priority, page, size));
    }

    @Operation(summary = "Get a test case by ID", description = "Retrieve a specific test case using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable String id) {
        return ResponseEntity.ok(service.getTestCaseById(id));
    }

    @Operation(summary = "Create a new test case", description = "Create a test case using title, description, status, and priority")
    @PostMapping
    public ResponseEntity<TestCase> createTestCase(
            @Valid @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam Status status,
            @RequestParam Priority priority) {
        
        TestCase newTestCase = service.createTestCase(title, description, status, priority);
        return ResponseEntity.status(201).body(newTestCase);
    }

    @Operation(summary = "Update an existing test case", description = "Modify an existing test case using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<TestCase> updateTestCase(@PathVariable String id, @Valid @RequestBody TestCase testCase) {
        return ResponseEntity.ok(service.updateTestCase(id, testCase));
    }

    @Operation(summary = "Delete a test case", description = "Remove a test case using its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestCase(@PathVariable String id) {
        service.deleteTestCase(id);
        return ResponseEntity.ok("Test case deleted successfully");
    }
}
