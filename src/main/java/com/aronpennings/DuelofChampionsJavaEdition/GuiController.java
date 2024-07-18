package com.aronpennings.DuelofChampionsJavaEdition;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class GuiController {
    @FXML
    private GuiStartup mainApp;
    private Stage stage;

    public void setMainApp(GuiStartup mainApp) {
        this.mainApp = mainApp;
    }
}
