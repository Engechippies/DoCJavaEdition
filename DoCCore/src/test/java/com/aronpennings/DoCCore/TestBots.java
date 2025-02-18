package com.aronpennings.DoCCore;

import com.aronpennings.DoCCore.DBManagement.DBManager;
import com.aronpennings.DoCCore.Player.NPC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestBots {
    @Test
    public void testBots() throws SQLException, ClassNotFoundException {
        DBManager dbManager = new DBManager();
        NPC npc = dbManager.getRandomBot();
        Assertions.assertNotNull(npc);
    }
}
