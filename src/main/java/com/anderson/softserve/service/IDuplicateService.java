package com.anderson.softserve.service;

import com.anderson.softserve.model.dto.DuplicateDto;
import com.anderson.softserve.model.dto.DuplicateResponseDto;

public interface IDuplicateService {

    DuplicateResponseDto noDuplicate(DuplicateDto request);

}
