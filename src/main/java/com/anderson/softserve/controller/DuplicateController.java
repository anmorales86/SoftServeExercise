package com.anderson.softserve.controller;

import com.anderson.softserve.model.dto.DuplicateDto;
import com.anderson.softserve.model.dto.DuplicateResponseDto;
import com.anderson.softserve.service.IDuplicateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DuplicateController {

    private final IDuplicateService iDuplicateService;

    @PostMapping("/no-duplicate")
    public ResponseEntity<DuplicateResponseDto> noDuplicate(@Valid @RequestBody final DuplicateDto request) {
        return new ResponseEntity<>(this.iDuplicateService.noDuplicate(request), HttpStatus.CREATED);
    }


}
