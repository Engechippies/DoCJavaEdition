package com.aronpennings.DuelofChampionsJavaEdition;

import java.util.Random;

public abstract class Player {
    String playerNaam;
    int dmg;
    int speed;
    int critmultiplier;
    int critchance;
    Random random = new Random();
    public Player(String playerNaam,int dmg,int speed,int critmultiplier, int critchance) {
        this.playerNaam = playerNaam;
        this.dmg = dmg;
        this.speed = speed;
        this.critmultiplier = critmultiplier;
        this.critchance = critchance;

    }
    public int Attack() {
        return dmg + (random.nextInt(2) - random.nextInt(2));
    }

    public String getPlayerNaam() {
        return playerNaam;
    }

    public int getCritchance() {
        return critchance;
    }

    public int getCritmultiplier() {
        return critmultiplier;
    }

    public int getDmg() {
        return dmg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPlayerNaam(String playerNaam) {
        this.playerNaam = playerNaam;
    }

    public void setCritchance(int critchance) {
        this.critchance = critchance;
    }

    public void setCritmultiplier(int critmultiplier) {
        this.critmultiplier = critmultiplier;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
