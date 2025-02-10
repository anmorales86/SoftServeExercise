package com.anderson.softserve.service.impl;

import com.anderson.softserve.model.LevelEnum;
import com.anderson.softserve.model.dto.DuplicateDto;
import com.anderson.softserve.model.dto.DuplicateResponseDto;
import com.anderson.softserve.service.IDuplicateService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DuplicateServiceImpl implements IDuplicateService {

    /**
     * Removes duplicate characters from the input string based on the specified experience level.
     *
     * @param request the DTO containing the input string and experience level.
     * @return a DTO containing the processed string without duplicates.
     * @throws HttpClientErrorException if the provided level is invalid.
     */
    public DuplicateResponseDto noDuplicate(DuplicateDto request)
    {
        DuplicateResponseDto responseDto = new DuplicateResponseDto();
        // Retrieve level from request
        LevelEnum levelEnum = LevelEnum.getByName(request.getLevel());
        if (Objects.isNull(levelEnum)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Level isn't valid. It's between 1 and 4");
        }
        // Apply the appropriate duplicate removal method based on level
        switch (levelEnum) {
            case JUNIOR:
                responseDto.setSentence(removeDuplicatesJunior(request.getSentence()));
                break;
            case SEMISENIOR:
                responseDto.setSentence(removeDuplicatesSemiSenior(request.getSentence()));
                break;
            case SENIOR:
                responseDto.setSentence(removeDuplicatesSenior(request.getSentence()));
                break;
            case SENIORADVANCE:
                responseDto.setSentence(removeDuplicatesSeniorAdvance(request.getSentence()));
                break;
        }
        return responseDto;
    }

    /**
     * Removes duplicate characters while maintaining order using a `LinkedHashSet` and a loop.
     * **Best for:** Junior developers.
     *
     * @param input the input string.
     * @return a string with duplicates removed.
     */
    public String removeDuplicatesJunior(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        Set<Character> seen = new LinkedHashSet<>();
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (seen.add(c)) { // Adds only if not already present
                result.append(c);
            }
        }

        return result.toString();
    }

    /**
     * Removes duplicate characters using Java Streams and `distinct()`.
     * **Best for:** Semisenior developers.
     *
     * @param input the input string.
     * @return a string with duplicates removed.
     */
    public String removeDuplicatesSemiSenior(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        return input.chars() // Convert String to IntStream
                .distinct() // Remove duplicates
                .mapToObj(c -> String.valueOf((char) c)) // Convert back to String
                .collect(Collectors.joining()); // Join back to a single String
    }

    /**
     * Removes duplicate characters while maintaining order using `LinkedHashSet` and Java Streams.
     * **Best for:** Senior developers.
     *
     * @param input the input string.
     * @return a string with duplicates removed.
     * @throws IllegalArgumentException if input is null.
     */
    public String removeDuplicatesSenior(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return input.chars()
                .collect(
                        LinkedHashSet::new, // Maintains order
                        (set, c) -> set.add((char) c),
                        LinkedHashSet::addAll
                )
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    /**
     * Removes duplicate characters using `Collectors.toMap()` for optimized performance and order retention.
     * **Best for:** Senior Advance developers.
     *
     * @param input the input string.
     * @return a string with duplicates removed.
     * @throws IllegalArgumentException if input is null.
     */
    public String removeDuplicatesSeniorAdvance(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(
                        Function.identity(), // Key: character itself
                        c -> c, // Value: character
                        (existing, replacement) -> existing, // Keep first occurrence
                        LinkedHashMap::new // Preserve order
                ))
                .values()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
