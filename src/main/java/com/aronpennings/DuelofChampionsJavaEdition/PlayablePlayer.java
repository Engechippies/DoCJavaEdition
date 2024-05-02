package com.aronpennings.DuelofChampionsJavaEdition;

public class PlayablePlayer extends Player{
    String playerNaam;
    int dmg;
    int speed;
    int critmultiplier;
    int critchance;
    public PlayablePlayer(String playerNaam,int dmg,int speed,int critmultiplier, int critchance) {
        super(playerNaam,dmg,speed,critmultiplier,critchance);
    }
}
