package com.aronpennings.DuelofChampionsJavaEdition;

public class NPC extends Player{
    String playerNaam;
    int dmg;
    int speed;
    int critmultiplier;
    int critchance;
    public NPC(String playerNaam,int dmg,int speed,int critmultiplier, int critchance) {
        super(playerNaam,dmg,speed,critmultiplier,critchance);
    }
}
