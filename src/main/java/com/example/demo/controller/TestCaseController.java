package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TestCase;
import com.example.demo.services.TestCaseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {
    
    @Autowired
    private TestCaseService service;
    
    @GetMapping
    public List<TestCase> getAllTestCases() {
        return service.getAllTestCases();
    }

    @GetMapping("/{id}")
    public Optional<TestCase> getTestCaseById(@PathVariable String id){
        return service.getTestCaseById(id);
    }

    @PostMapping
    public TestCase createTestCase(@RequestBody TestCase testCase) {
        return service.createTestCase(testCase);
    }

    @PutMapping("/{id}")
    public TestCase updateTestCase(@PathVariable String id, @RequestBody TestCase testCase){
        return service.updateTestCase(id, testCase);
    }

    @DeleteMapping("/{id}")
    public void deleteTestCase(@PathVariable String id){
        service.deleteTestCase(id);
    }
}
