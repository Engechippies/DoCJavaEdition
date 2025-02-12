package com.aronpennings.DoCCore.Modes;

import com.aronpennings.DoCCore.DBManagement.DBManager;
import com.aronpennings.DoCCore.Player.NPC;
import com.aronpennings.DoCCore.Player.Player;
import com.aronpennings.DoCCore.Player.PlayerBehaviour;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class OneVOne implements IMode{
    DBManager dbManager = new DBManager();
    Player player = dbManager.getPlayer(1);
    ArrayList<NPC> npcs;


    public OneVOne() throws SQLException, ClassNotFoundException {
        npcs = new ArrayList<>();
        npcs.add(dbManager.getRandomBot());
    }

    @Override
    public ArrayList<NPC> getNpcs() {
        return npcs;
    }
    public NPC getNPC() {
        return npcs.getFirst();
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
