package com.aronpennings.DoCCore.Modes;

import com.aronpennings.DoCCore.DBManagement.DBManager;
import com.aronpennings.DoCCore.Player.Player;

import java.sql.SQLException;

public class Settings{
    DBManager dbManager;
    Player player;
    {
        try {
            dbManager = new DBManager();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        player = dbManager.getPlayer(1);
    }
    public String[] giveDifficultyInformationToGui() {
        String[] difficulties = {"Baby","Normal","Hard","Sadism"};
        return difficulties;
    }
    public Player getPlayer() {
        return player;
    }
    public void setDifficulty(String difficulty) throws SQLException {
        dbManager.ChangeDifficulty(difficulty);
    }
    public void setMode(String mode) throws SQLException {
        dbManager.setCurrentMode(mode);
    }
    public void setNaam(String naam) throws SQLException {
        dbManager.ChangeNaam(naam);
    }
    public void setStats(int id, int damage, int speed, int hp, int critchance) throws SQLException, ClassNotFoundException {
        if (damage != -1) {
            dbManager.ChangeDamage(damage);
        }
        if (speed != -1) {
            dbManager.ChangeSpeed(speed);
        }
        if (hp != -1) {
            dbManager.ChangeHp(hp);
        }
        if (critchance != -1) {
            dbManager.ChangeCritChance(critchance);
        }
    }
}
