package com.anderson.softserve.controller;

import com.anderson.softserve.model.dto.DuplicateDto;
import com.anderson.softserve.model.dto.DuplicateResponseDto;
import com.anderson.softserve.service.IDuplicateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DuplicateControllerTest {

    @Mock
    private IDuplicateService iDuplicateService;


    @InjectMocks
    private DuplicateController duplicateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNoDuplicate_Success() {
        // Arrange
        DuplicateDto duplicateDto = new DuplicateDto();
        duplicateDto.setSentence("AABBCCD112233");
        duplicateDto.setLevel("3");

        DuplicateResponseDto responseDto = new DuplicateResponseDto();
        responseDto.setSentence("ABCD123");

        when(iDuplicateService.noDuplicate(duplicateDto)).thenReturn(responseDto);

        // Act
        ResponseEntity<DuplicateResponseDto> response = duplicateController.noDuplicate(duplicateDto);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(responseDto.getSentence(), response.getBody().getSentence());
    }

}
