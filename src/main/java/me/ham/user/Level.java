package me.ham.user;

public enum Level {
    GOLD(3, null),
    SILVER(2, GOLD),
    BASIC(1, SILVER);

    private final int value;
    private final Level nextLevel;

    Level(int value, Level nextValue) {
        this.value = value;
        this.nextLevel = nextValue;
    }

    public int getValue() {
        return value;
    }

    public Level nextLevel() {
        return this.nextLevel;
    }
}
