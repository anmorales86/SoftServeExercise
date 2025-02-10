package com.anderson.softserve.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DuplicateDto {

    @NotBlank(message = "Sentence shouldn't be null or empty.")
    private String sentence;
    @NotBlank(message = "Level shouldn't be null or empty.")
    private String level;

}
