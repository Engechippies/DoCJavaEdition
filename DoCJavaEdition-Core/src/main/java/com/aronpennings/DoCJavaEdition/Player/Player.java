package com.aronpennings.DoCJavaEdition.Player;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name="Player")
public class Player implements PlayerBehaviour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name="naam")
    String naam;
    @Column(name="damage")
    int damage;
    @Column(name="speed")
    int speed;
    @Column(name="hp")
    int hp;
    @Column(name="critChance")
    int critchance;
    @Column(name = "difficulty")
    String difficulty;
    @Column(name = "coins")
    int coins;
    @Column(name = "currentMode")
    String currentMode;
    @Column(name = "volume")
    double volume;
    String move = null;
    public Player(String naam, int damage, int speed, int hp, int critchance, String difficulty, int coins, String currentMode, double volume) {
        this.naam = naam;
        this.damage = damage;
        this.speed = speed;
        this.hp = hp;
        this.critchance = critchance;
        this.difficulty = difficulty;
        this.coins = coins;
        this.currentMode = currentMode;
        this.volume = volume;
    }
    public Player() {}
    public int Attack() {
        Random random = new Random();
        if ((6 * critchance) > random.nextInt(100)) {
            return (damage * 2) - random.nextInt(2);
        } else {
            return damage + (random.nextInt(2) - random.nextInt(2));
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

    public void setCurrentMode(String currentMode) {
        this.currentMode = currentMode;
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

    public String getDifficulty() {return difficulty;}

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setCritchance(int critchance) {
        this.critchance = critchance;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public String getCurrentMode() {
        return currentMode;
    }
}
