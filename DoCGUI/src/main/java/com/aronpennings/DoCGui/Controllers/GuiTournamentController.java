package com.aronpennings.DoCGui.Controllers;

import com.aronpennings.DoCGui.Gui;
import javafx.stage.Stage;

public class GuiTournamentController {
    private Stage stage;
    private Gui mainApp;

    public void setMainApp(Gui mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
