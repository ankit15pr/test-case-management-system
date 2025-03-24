package com.example.demo.services;

import com.example.demo.Repository.TestCaseRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TestCase;
import com.example.demo.model.Priority;
import com.example.demo.model.Status;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestCaseServiceTest {
    
    @Mock
    private TestCaseRepository testCaseRepository;

    @InjectMocks
    private TestCaseService testCaseService;

    private TestCase testCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testCase = new TestCase("1", "Login Test", "Check login functionality", Status.PENDING, Priority.HIGH, null, null);
    }

    // ✅ Test creating a test case
    @Test
    void testCreateTestCase() {
        when(testCaseRepository.save(any(TestCase.class))).thenReturn(testCase);

        TestCase created = testCaseService.createTestCase("Login Test", "Check login functionality", Status.PENDING, Priority.HIGH);

        assertNotNull(created);
        assertEquals("Login Test", created.getTitle());
        verify(testCaseRepository, times(1)).save(any(TestCase.class));
    }

    // ✅ Test retrieving a test case by ID (Success)
    @Test
    void testGetTestCaseById_Success() {
        when(testCaseRepository.findById("1")).thenReturn(Optional.of(testCase));

        TestCase found = testCaseService.getTestCaseById("1");

        assertNotNull(found);
        assertEquals("Login Test", found.getTitle());
    }

    // ❌ Test retrieving a non-existing test case (Throws Exception)
    @Test
    void testGetTestCaseById_NotFound() {
        when(testCaseRepository.findById("99")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> testCaseService.getTestCaseById("99"));
    }

    // ✅ Test updating an existing test case
    @Test
    void testUpdateTestCase() {
        TestCase updatedTestCase = new TestCase("1", "Updated Test", "Updated description", Status.PASSED, Priority.MEDIUM, null, null);

        when(testCaseRepository.findById("1")).thenReturn(Optional.of(testCase));
        when(testCaseRepository.save(any(TestCase.class))).thenReturn(updatedTestCase);

        TestCase result = testCaseService.updateTestCase("1", updatedTestCase);

        assertEquals("Updated Test", result.getTitle());
        verify(testCaseRepository, times(1)).save(any(TestCase.class));
    }

    // ❌ Test updating a non-existing test case (Throws Exception)
    @Test
    void testUpdateTestCase_NotFound() {
        TestCase updatedTestCase = new TestCase("99", "Updated Test", "Updated description", Status.PASSED, Priority.MEDIUM, null, null);

        when(testCaseRepository.findById("99")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> testCaseService.updateTestCase("99", updatedTestCase));
    }

    // ✅ Test deleting a test case
    @Test
    void testDeleteTestCase() {
        when(testCaseRepository.existsById("1")).thenReturn(true);
        doNothing().when(testCaseRepository).deleteById("1");

        assertDoesNotThrow(() -> testCaseService.deleteTestCase("1"));
        verify(testCaseRepository, times(1)).deleteById("1");
    }

    // ❌ Test deleting a non-existing test case (Throws Exception)
    @Test
    void testDeleteTestCase_NotFound() {
        when(testCaseRepository.existsById("99")).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> testCaseService.deleteTestCase("99"));
    }
}
