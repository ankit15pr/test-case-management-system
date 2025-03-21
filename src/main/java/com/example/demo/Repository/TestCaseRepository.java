package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Priority;
import com.example.demo.model.Status;
import com.example.demo.model.TestCase;

public interface TestCaseRepository extends MongoRepository<TestCase, String> {
    List<TestCase> findByStatusAndPriority(Status stutus, Priority priority);
}
