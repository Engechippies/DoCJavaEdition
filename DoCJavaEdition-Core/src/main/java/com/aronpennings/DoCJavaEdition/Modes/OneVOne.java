package com.aronpennings.DoCJavaEdition.Modes;

import com.aronpennings.DoCJavaEdition.DBManagement.DBManager;
import com.aronpennings.DoCJavaEdition.Player.NPC;
import com.aronpennings.DoCJavaEdition.Player.Player;
import com.aronpennings.DoCJavaEdition.Player.PlayerBehaviour;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class OneVOne implements IMode{
    DBManager dbManager = new DBManager();
    Player player;
    NPC npc;

    public OneVOne() throws SQLException, ClassNotFoundException {
        this.npc = dbManager.getRandomBot();
        this.player = dbManager.getPlayer(1);
    }
    public OneVOne(NPC npc) throws SQLException, ClassNotFoundException {
        this.npc = npc;
        this.player = dbManager.getPlayer(1);
    }

    public NPC getNPC() {
        return npc;
    }

    public int Attack(PlayerBehaviour playerToAttack, PlayerBehaviour playerGettingAttacked) {
        Random random = new Random();
        int attack = playerToAttack.Attack();
        if ((5 * playerGettingAttacked.getSpeed()) > random.nextInt(100)) {
            return 0;
        }
        if (playerGettingAttacked.getMove().equals("block")) {
            return 0;
        }else {
            return attack;
        }
    }
    public String Block() {
        return "block";
    }

    public int Heal() {
        return new Random().nextInt(4);
    }



    @Override
    public Player getPlayer() {
        return player;
    }

    public Player refreshPlayer() {
        return dbManager.getPlayer(1);
    }

    @Override
    public void setPlayer(int id) {

    }
}
