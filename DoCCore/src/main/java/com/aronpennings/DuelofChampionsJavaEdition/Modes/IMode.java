package com.aronpennings.DuelofChampionsJavaEdition.Modes;

import com.aronpennings.DuelofChampionsJavaEdition.DBManagement.DBManager;
import com.aronpennings.DuelofChampionsJavaEdition.Player.NPC;
import com.aronpennings.DuelofChampionsJavaEdition.Player.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IMode {
    public ArrayList<NPC> npcs = null;

    public Player player = null;

    public ArrayList<NPC> getNpcs() throws SQLException;

    public Player getPlayer();

    public void setPlayer(int id);
}
