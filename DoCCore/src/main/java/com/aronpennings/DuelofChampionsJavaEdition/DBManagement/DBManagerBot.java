package com.aronpennings.DuelofChampionsJavaEdition.DBManagement;

import com.aronpennings.DuelofChampionsJavaEdition.Player.Difficulty.Baby;
import com.aronpennings.DuelofChampionsJavaEdition.Player.Difficulty.Hard;
import com.aronpennings.DuelofChampionsJavaEdition.Player.Difficulty.Normal;
import com.aronpennings.DuelofChampionsJavaEdition.Player.Difficulty.Sadism;
import com.aronpennings.DuelofChampionsJavaEdition.Player.NPC;
import com.aronpennings.DuelofChampionsJavaEdition.Player.NPCStrings.NPCStrings;
import com.aronpennings.DuelofChampionsJavaEdition.Player.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.Random;

public class DBManagerBot {
    public NPC getRandomBot(SessionFactory sessionFactory) throws SQLException {
        Session session = sessionFactory.openSession();
        Player player = session.get(Player.class, 1);
        if (player.getDifficulty().equals("Baby")) {
            return new NPC(Baby.DMG.dmg(), Baby.SPEED.speed(), Baby.HEALTH.health(), Baby.CRITCHANCE.critChance());
        }
        if (player.getDifficulty().equals("Normal")) {
            return new NPC(Normal.DMG.dmg(), Normal.SPEED.speed(), Normal.HEALTH.health(), Normal.CRITCHANCE.critChance());
        }
        if (player.getDifficulty().equals("Hard")) {
            return new NPC(Hard.DMG.dmg(), Hard.SPEED.speed(), Hard.HEALTH.health(), Hard.CRITCHANCE.critChance());
        }
        else if (player.getDifficulty().equals("Sadism")) {
            return new NPC(Sadism.DMG.dmg(), Sadism.SPEED.speed(), Sadism.HEALTH.health(), Sadism.CRITCHANCE.critChance());
        }
        return null;
    }
}
