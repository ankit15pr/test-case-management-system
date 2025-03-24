package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.TestCase;
import com.example.demo.model.Status;
import com.example.demo.model.Priority;
import com.example.demo.services.TestCaseService;

import java.util.Optional;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {
    
    @Autowired
    private TestCaseService service;

    // ✅ Get all test cases with pagination & filtering
    @GetMapping
    public Page<TestCase> getAllTestCases(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAllTestCases(status, priority, page, size);
    }

    // ✅ Get test case by ID
    @GetMapping("/{id}")
    public Optional<TestCase> getTestCaseById(@PathVariable String id){
        return service.getTestCaseById(id);
    }

    // ✅ Create a new test case
    @PostMapping
    public TestCase createTestCase(@RequestBody TestCase testCase) {
        return service.createTestCase(testCase);
    }

    // ✅ Update an existing test case
    @PutMapping("/{id}")
    public TestCase updateTestCase(@PathVariable String id, @RequestBody TestCase testCase){
        return service.updateTestCase(id, testCase);
    }

    // ✅ Delete a test case
    @DeleteMapping("/{id}")
    public void deleteTestCase(@PathVariable String id){
        service.deleteTestCase(id);
    }
}
