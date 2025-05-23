package com.aronpennings.DoCJavaEdition.Modes;

import com.aronpennings.DoCJavaEdition.DBManagement.DBManager;
import com.aronpennings.DoCJavaEdition.Player.NPC;
import com.aronpennings.DoCJavaEdition.Player.Player;

import java.sql.SQLException;
import java.util.ArrayList;

public class Tournament implements IMode{
    public DBManager dbManager = new DBManager();
    private Player player;
    private ArrayList<NPC> npcs;
    private String naam;

    public Tournament(int aantalBots, Player player, String naam) throws SQLException, ClassNotFoundException {
        this.naam = naam;
        this.player = player;
        npcs = new ArrayList<>();
        for (int i = 0; i <= aantalBots; i++) {
            npcs.add(dbManager.getRandomBot());
        }
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    public String getNaam() {
        return naam;
    }

    public NPC getSingularNPC(int spotInList) {
        return npcs.get(spotInList);
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public void setPlayer(int id) {
        dbManager.getPlayer(1);
    }
}
