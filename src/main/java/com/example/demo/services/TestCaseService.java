package com.example.demo.services;

import com.example.demo.Repository.TestCaseRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.factory.TestCaseFactory;
import com.example.demo.model.TestCase;
import com.example.demo.model.Status;
import com.example.demo.model.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TestCaseService {
    
    @Autowired
    private TestCaseRepository repository;

    // ✅ Get all test cases (with pagination & filtering)
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

    // ✅ Get test case by ID (Exception Handling)
    public TestCase getTestCaseById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test case not found with ID: " + id));
    }

    // ✅ Create a test case using Factory Pattern
    public TestCase createTestCase(String title, String description, Status status, Priority priority) {
        TestCase testCase = TestCaseFactory.createTestCase(title, description, status, priority);
        return repository.save(testCase);
    }

    // ✅ Update test case (Exception Handling)
    public TestCase updateTestCase(String id, TestCase updatedTestCase) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(updatedTestCase.getTitle());
            existing.setDescription(updatedTestCase.getDescription());
            existing.setStatus(updatedTestCase.getStatus());
            existing.setPriority(updatedTestCase.getPriority());
            existing.setUpdatedAt(new java.util.Date());
            return repository.save(existing);
        }).orElseThrow(() -> new ResourceNotFoundException("Test case not found with ID: " + id));
    }

    // ✅ Delete test case (Exception Handling)
    public void deleteTestCase(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Test case not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
