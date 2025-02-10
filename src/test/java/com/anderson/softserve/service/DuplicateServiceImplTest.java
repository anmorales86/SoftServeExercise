package com.anderson.softserve.service;

import com.anderson.softserve.model.dto.DuplicateDto;
import com.anderson.softserve.model.dto.DuplicateResponseDto;
import com.anderson.softserve.service.impl.DuplicateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DuplicateServiceImplTest {

    private DuplicateServiceImpl duplicateService;

    @BeforeEach
    void setUp() {
        duplicateService = new DuplicateServiceImpl();
    }

    // 🔹 Test for Junior Level
    @Test
    void testRemoveDuplicatesJunior() {
        assertEquals("ABCD123", duplicateService.removeDuplicatesJunior("AABBCCD112233"));
        assertEquals("abc", duplicateService.removeDuplicatesJunior("aaabbccc"));
        assertEquals("a", duplicateService.removeDuplicatesJunior("aaaa"));
        assertEquals("", duplicateService.removeDuplicatesJunior(""));
    }

    // 🔹 Test for Semisenior Level
    @Test
    void testRemoveDuplicatesSemiSenior() {
        assertEquals("ABCD123", duplicateService.removeDuplicatesSemiSenior("AABBCCD112233"));
        assertEquals("abc", duplicateService.removeDuplicatesSemiSenior("aaabbccc"));
        assertEquals("a", duplicateService.removeDuplicatesSemiSenior("aaaa"));
        assertEquals("", duplicateService.removeDuplicatesSemiSenior(""));
    }

    // 🔹 Test for Senior Level
    @Test
    void testRemoveDuplicatesSenior() {
        assertEquals("ABCD123", duplicateService.removeDuplicatesSenior("AABBCCD112233"));
        assertEquals("abc", duplicateService.removeDuplicatesSenior("aaabbccc"));
        assertEquals("a", duplicateService.removeDuplicatesSenior("aaaa"));
        assertEquals("", duplicateService.removeDuplicatesSenior(""));
    }

    // 🔹 Test for Senior Advance Level
    @Test
    void testRemoveDuplicatesSeniorAdvance() {
        assertEquals("ABCD123", duplicateService.removeDuplicatesSeniorAdvance("AABBCCD112233"));
        assertEquals("abc", duplicateService.removeDuplicatesSeniorAdvance("aaabbccc"));
        assertEquals("a", duplicateService.removeDuplicatesSeniorAdvance("aaaa"));
        assertEquals("", duplicateService.removeDuplicatesSeniorAdvance(""));
    }

    // 🔹 Test for null input in Senior methods (should throw an exception)
    @Test
    void testRemoveDuplicatesSeniorWithNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> duplicateService.removeDuplicatesSenior(null));
        assertEquals("Input cannot be null", exception.getMessage());
    }

    @Test
    void testRemoveDuplicatesSeniorAdvanceWithNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> duplicateService.removeDuplicatesSeniorAdvance(null));
        assertEquals("Input cannot be null", exception.getMessage());
    }

    // 🔹 Test for noDuplicate() method with different levels
    @Test
    void testNoDuplicateWithValidLevels() {
        DuplicateDto request = new DuplicateDto("AABBCCD112233", "1");
        DuplicateResponseDto response = duplicateService.noDuplicate(request);
        assertEquals("ABCD123", response.getSentence());

        request = new DuplicateDto("AABBCCD112233", "2");
        response = duplicateService.noDuplicate(request);
        assertEquals("ABCD123", response.getSentence());

        request = new DuplicateDto("AABBCCD112233", "3");
        response = duplicateService.noDuplicate(request);
        assertEquals("ABCD123", response.getSentence());

        request = new DuplicateDto("AABBCCD112233", "4");
        response = duplicateService.noDuplicate(request);
        assertEquals("ABCD123", response.getSentence());
    }


}
