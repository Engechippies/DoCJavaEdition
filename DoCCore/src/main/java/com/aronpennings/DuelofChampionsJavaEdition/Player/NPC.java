package com.aronpennings.DuelofChampionsJavaEdition.Player;

import com.aronpennings.DuelofChampionsJavaEdition.Player.NPCStrings.NPCStrings;

public class NPC implements PlayerBehaviour {
    int dmg;
    int speed;
    int hp;
    int critchance;
    String naam;
    String move;

    public NPC(int dmg, int speed, int hp, int critchance) {
        this.naam = NPCStrings.NPC_STRINGS.getName();
        this.dmg = dmg;
        this.speed = speed;
        this.hp = hp;
        this.critchance = critchance;
        this.move = "nothing";
    }

    @Override
    public int Attack() {
        if ((6 * critchance) > random.nextInt(100)) {
            return (dmg * 2) - random.nextInt(2);
        } else {
            return dmg - random.nextInt(2);
        }
    }

    @Override
    public void setHealth(int newHealth) {
        this.hp = newHealth;
    }

    @Override
    public void setMove(String move) {
        this.move = move;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    public int getCritchance() {
        return critchance;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public int getHealth() {
        return hp;
    }

    @Override
    public String getName() {
        return naam;
    }

    @Override
    public String getMove() {
        return move;
    }
}
