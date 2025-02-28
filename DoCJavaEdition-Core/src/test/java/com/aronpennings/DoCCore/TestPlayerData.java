package com.aronpennings.DoCCore;

import com.aronpennings.DoCJavaEdition.DBManagement.DBManager;
import com.aronpennings.DoCJavaEdition.Player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestPlayerData {

    @Test
    public void TestChangeDifficulty() throws SQLException, ClassNotFoundException {
        DBManager dbManager = new DBManager();
        Player initialPlayer = dbManager.getPlayer(1);
        dbManager.ChangeDifficulty("Test");
        Player playerWithTestDifficulty = dbManager.getPlayer(1);
        dbManager.ChangeDifficulty(initialPlayer.getDifficulty());
        Assertions.assertTrue(playerWithTestDifficulty.getDifficulty().equals("Test"));
    }
}
