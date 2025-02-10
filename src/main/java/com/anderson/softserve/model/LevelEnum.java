package com.anderson.softserve.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

@Getter
public enum LevelEnum {

    JUNIOR("1"),
    SEMISENIOR("2"),
    SENIOR("3"),
    SENIORADVANCE("4");

    private String name;

    LevelEnum(String name) {
        this.name = name;
    }

    public static Stream<LevelEnum> stream() {
        return Stream.of(LevelEnum.values());
    }

    public static LevelEnum getByName(String value) {
        return Arrays.stream(LevelEnum.values()).filter(enumRole -> value.contains(enumRole.getName())).findFirst().orElse(null);
    }

}
