package com.aronpennings.DoCCore.Player.Difficulty;

import java.util.Random;

public enum Normal {
    DMG(2, 5),
    HEALTH(4, 8),
    SPEED(2, 5),
    CRITCHANCE(1,4);

    private final int minValue;
    private final int maxValue;
    Normal(int minValue, int maxValue) {
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
