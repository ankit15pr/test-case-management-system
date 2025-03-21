package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Repository.TestCaseRepository;
import com.example.demo.model.TestCase;

public class TestCaseService {
    
    @Autowired
    private TestCaseRepository repository;

    public List<TestCase> getAllTestCases() {
        return repository.findAll();
    }

    public Optional<TestCase> getTestCaseById(String id) {
        return repository.findById(id);
    }

    public TestCase createTestCase(TestCase testCase) {
        testCase.setCreatedAt(new java.util.Date());
        return repository.save(testCase);
    }

    public TestCase updateTestCase(String id, TestCase updatedTestCase) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(updatedTestCase.getTitle());
            existing.setDescription(updatedTestCase.getDescription());
            existing.setStatus(updatedTestCase.getStatus());
            existing.setPriority(updatedTestCase.getPriority());
            existing.setUpdatedAt(new java.util.Date());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Test case not found"));
    }

    public void deleteTestCase(String id) {
        repository.deleteById(id);
    }
}
