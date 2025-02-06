package com.aronpennings.Gui.Controllers;

import com.aronpennings.DuelofChampionsJavaEdition.DBManagement.DBManager;
import com.aronpennings.Gui.Gui;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class GuiStartController {
    public Button exitKnop;
    public TextField modeView;

    @FXML
    private Gui mainApp;
    private Stage stage;
    private DBManager dbManager;

    public GuiStartController() throws SQLException, ClassNotFoundException {
        dbManager = new DBManager();
    }

    public void initialize() {
        try {
            String mode = dbManager.getPlayer(1).getCurrentMode();
            if (mode == null) {
                throw new NullPointerException();
            } else {
                modeView.setText(mode);
            }
        }
        catch (NullPointerException nullPointerException) {
            modeView.setText("No mode selected");
        }
    }
    public void setMainApp(Gui mainApp) {
        this.mainApp = mainApp;
    }
    public void leaveGame(javafx.event.ActionEvent actionEvent) {
        mainApp.leaveConfirmatie.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK) {
                stage.close();
            }
        });
    }
    public void onButtonClick(javafx.event.ActionEvent actionEvent) {
       // if (dbManager.getPlayer(1).getCurrentMode() != null) {
            mainApp.launchOneVOne(stage);
        //} else {
        //    new Alert(Alert.AlertType.ERROR, "Sorry, no game mode is currently selected, go into settings to set a mode").showAndWait();
        //}
    }
    public void onSettingsClick(javafx.event.ActionEvent actionEvent) {mainApp.launchSettings(stage);}

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
