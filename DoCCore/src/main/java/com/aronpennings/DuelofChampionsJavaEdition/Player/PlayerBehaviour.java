package com.aronpennings.DuelofChampionsJavaEdition.Player;

import java.util.Random;

public interface PlayerBehaviour
{
    Random random = new Random();
    int damage = 0;
    String naam = "";
    int critchance = 0;
    int speed = 0;
    int health = 0;
    String move = "";
    public int Attack();

    public void setHealth(int newHealth);
    public void setMove(String move);

    public default String getNaam() {
        return naam;
    }

    public default int getCritchance() {
        return critchance;
    }

    public default int getDamage() {
        return damage;
    }

    public default int getSpeed() {
        return speed;
    }
    public default int getHealth() {return health;}
    public default String getName() {return naam;}
    public default String getMove() {return move;}
}
