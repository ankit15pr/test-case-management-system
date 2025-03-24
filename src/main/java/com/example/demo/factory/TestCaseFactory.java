package com.example.demo.factory;

import com.example.demo.model.TestCase;
import com.example.demo.model.Status;
import com.example.demo.model.Priority;
import java.util.Date;

public class TestCaseFactory {

    public static TestCase createTestCase(String title, String description, Status status, Priority priority) {
        TestCase testCase = new TestCase();
        testCase.setTitle(title);
        testCase.setDescription(description);
        testCase.setStatus(status);
        testCase.setPriority(priority);
        testCase.setCreatedAt(new Date());
        return testCase;
    }
}
