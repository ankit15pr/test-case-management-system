package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.TestCaseRepository;
import com.example.demo.model.TestCase;
import com.example.demo.model.Status;
import com.example.demo.model.Priority;

@Service
public class TestCaseService {
    
    @Autowired
    private TestCaseRepository repository;

    // ✅ Pagination & Filtering for GET /api/testcases
    public Page<TestCase> getAllTestCases(Status status, Priority priority, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        
        if (status != null && priority != null) {
            return repository.findByStatusAndPriority(status, priority, pageable);
        } else if (status != null) {
            return repository.findByStatus(status, pageable);
        } else if (priority != null) {
            return repository.findByPriority(priority, pageable);
        }
        
        return repository.findAll(pageable);
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
