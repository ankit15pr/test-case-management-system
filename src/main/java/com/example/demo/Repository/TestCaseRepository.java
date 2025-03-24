package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Priority;
import com.example.demo.model.Status;
import com.example.demo.model.TestCase;

public interface TestCaseRepository extends MongoRepository<TestCase, String> {
    Page<TestCase> findByStatus(Status status, org.springframework.data.domain.Pageable pageable);
    
    Page<TestCase> findByPriority(Priority priority, org.springframework.data.domain.Pageable pageable);

    Page<TestCase> findByStatusAndPriority(Status status, Priority priority, org.springframework.data.domain.Pageable pageable);
}
