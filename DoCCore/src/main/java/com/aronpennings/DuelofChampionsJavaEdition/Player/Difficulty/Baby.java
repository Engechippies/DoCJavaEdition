package com.aronpennings.DuelofChampionsJavaEdition.Player.Difficulty;

import java.util.Random;

public enum Baby {
    DMG(1, 3),
    HEALTH(2, 6),
    SPEED(1, 3),
    CRITCHANCE(1,3);

    private final int minValue;
    private final int maxValue;
    Baby(int minValue, int maxValue) {
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
