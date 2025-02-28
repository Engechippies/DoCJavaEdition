package com.aronpennings.DoCJavaEdition.Player.Difficulty;

import java.util.Random;

public enum Sadism {
    DMG(5, 8),
    HEALTH(8, 12),
    SPEED(4, 8),
    CRITCHANCE(2,5);

    private final int minValue;
    private final int maxValue;
    Sadism(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    Random random = new Random();
    public int dmg() {
        return random.nextInt(maxValue - minValue) + minValue;
    }
    public int health() {
        return random.nextInt(maxValue - minValue) + minValue;
    }
    public int speed() {
        return random.nextInt(maxValue - minValue) + minValue;
    }
    public int critChance() {
        return random.nextInt(maxValue - minValue) + minValue;
    }
}
