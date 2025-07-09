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

import java.util.Date;

@Tag(name = "Test Case Management", description = "CRUD operations for test cases")
@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {

    @Autowired
    private TestCaseService service;

    @Operation(summary = "Get all test cases", description = "Retrieve a paginated list of test cases with optional filtering by status and priority")
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

    @Operation(summary = "Create a new test case", description = "Create a test case using JSON body")
    @PostMapping
    public ResponseEntity<TestCase> createTestCase(@Valid @RequestBody TestCase request) {
        request.setCreatedAt(new Date());
        request.setUpdatedAt(new Date());
        TestCase newTestCase = service.createTestCase(
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                request.getPriority());
        return ResponseEntity.status(201).body(newTestCase);
    }

    @Operation(summary = "Update a test case", description = "Update a test case using its ID and new JSON body")
    @PatchMapping("/{id}")
    public ResponseEntity<TestCase> patchTestCase(@PathVariable String id, @RequestBody TestCase request) {
        return ResponseEntity.ok(service.updateTestCase(id, request));
    }

    @Operation(summary = "Delete a test case", description = "Delete a test case by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestCase(@PathVariable String id) {
        service.deleteTestCase(id);
        return ResponseEntity.ok("Test case deleted successfully");
    }
}
