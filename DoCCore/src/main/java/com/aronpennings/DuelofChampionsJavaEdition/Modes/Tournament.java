package com.aronpennings.DuelofChampionsJavaEdition.Modes;

import com.aronpennings.DuelofChampionsJavaEdition.Player.NPC;
import com.aronpennings.DuelofChampionsJavaEdition.Player.Player;

import java.sql.SQLException;
import java.util.ArrayList;

public class Tournament implements IMode{

    @Override
    public ArrayList<NPC> getNpcs() throws SQLException {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public void setPlayer(int id) {

    }
}
