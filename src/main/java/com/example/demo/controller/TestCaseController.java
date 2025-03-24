package com.example.demo.controller;

import com.example.demo.model.TestCase;
import com.example.demo.model.Status;
import com.example.demo.model.Priority;
import com.example.demo.services.TestCaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {
    
    @Autowired
    private TestCaseService service;

    // ✅ Get all test cases (with pagination & filtering)
    @GetMapping
    public ResponseEntity<Page<TestCase>> getAllTestCases(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAllTestCases(status, priority, page, size));
    }

    // ✅ Get test case by ID (Returns 404 if not found)
    @GetMapping("/{id}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable String id) {
        return ResponseEntity.ok(service.getTestCaseById(id));
    }

    // ✅ Create a new test case (Factory Pattern)
    @PostMapping
    public ResponseEntity<TestCase> createTestCase(
            @Valid @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam Status status,
            @RequestParam Priority priority) {
        
        TestCase newTestCase = service.createTestCase(title, description, status, priority);
        return ResponseEntity.status(201).body(newTestCase);
    }

    // ✅ Update an existing test case (Returns 404 if not found)
    @PutMapping("/{id}")
    public ResponseEntity<TestCase> updateTestCase(@PathVariable String id, @Valid @RequestBody TestCase testCase) {
        return ResponseEntity.ok(service.updateTestCase(id, testCase));
    }

    // ✅ Delete a test case (Returns 404 if not found)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestCase(@PathVariable String id) {
        service.deleteTestCase(id);
        return ResponseEntity.ok("Test case deleted successfully");
    }
}
