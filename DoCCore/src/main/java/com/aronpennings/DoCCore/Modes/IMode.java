package com.aronpennings.DoCCore.Modes;

import com.aronpennings.DoCCore.Player.NPC;
import com.aronpennings.DoCCore.Player.Player;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMode {
    public ArrayList<NPC> npcs = null;

    public Player player = null;

    public ArrayList<NPC> getNpcs() throws SQLException;

    public Player getPlayer();

    public void setPlayer(int id);
}
