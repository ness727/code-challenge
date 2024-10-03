package com.megamaker.codechallenge.domain;

public enum Level {
    LV0("레벨 0"),
    LV1("레벨 1"),
    LV2("레벨 2"),
    LV3("레벨 3"),
    LV4("레벨 4"),
    LV5("레벨 5");

    private final String levelString;

    Level(String levelString) {
        this.levelString = levelString;
    }

    public String getLevelString() {
        return levelString;
    }
}
