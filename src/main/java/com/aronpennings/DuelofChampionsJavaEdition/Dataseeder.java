package com.aronpennings.DuelofChampionsJavaEdition;

public class Dataseeder {
    UI ui;
    private String playerNaam;
    public Dataseeder() {
    }
    public void application() {
        switch(ui.keuzeMenu()) {
            case "onevone":
                OneVOne oneVOne = new OneVOne();
                break;
            case "tournament":
                Tournament tournament = new Tournament();
                break;
            case "settings":
                Settings settings = new Settings();
                break;
            default:
                break;
        }
    }
    public String getPlayerNaam() {
        return playerNaam;
    }

    public void setPlayerNaam(String playerNaam) {
        this.playerNaam = playerNaam;
    }
}
