package com.megamaker.codechallenge.problem.domain.vo;

public enum Level {
    LV0(0, "LV0", "레벨 0"),
    LV1(1, "LV1", "레벨 1"),
    LV2(2, "LV2", "레벨 2"),
    LV3(3, "LV3", "레벨 3"),
    LV4(4, "LV4", "레벨 4"),
    LV5(5, "LV5", "레벨 5");

    private final int level;
    private final String levelName;
    private final String levelString;

    Level(int level, String levelName, String levelString) {
        this.level = level;
        this.levelName = levelName;
        this.levelString = levelString;
    }

    public int getLevel() {
        return level;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getLevelString() {
        return levelString;
    }

    public static Level intToEnum(Integer level) {
        if (level == null) return null;
        for (Level value : Level.values()) {
            if (value.getLevel() == level) return value;
        }
        return null;
    }
}
