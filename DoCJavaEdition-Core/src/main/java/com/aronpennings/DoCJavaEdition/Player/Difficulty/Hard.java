
package com.aronpennings.DoCJavaEdition.Player.Difficulty;

import java.util.Random;

public enum Hard {
    DMG(3, 6),
    HEALTH(6, 10),
    SPEED(3, 6),
    CRITCHANCE(2,4);

    private final int minValue;
    private final int maxValue;
    Hard(int minValue, int maxValue) {
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
