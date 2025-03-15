package com.aronpennings.DoCJavaEdition.Controllers;

import com.aronpennings.DoCJavaEdition.DBManagement.DBManager;
import com.aronpennings.DoCJavaEdition.Player.Player;
import com.aronpennings.DoCJavaEdition.Gui;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class GuiStartController {
    public Button exitKnop;
    public TextField modeView;
    public TextField difficultyView;

    @FXML
    private Gui mainApp;
    private Stage stage;
    private DBManager dbManager;
    private Player player;

    public GuiStartController() throws SQLException, ClassNotFoundException {
        dbManager = new DBManager();
    }

    public void initialize() {
        player = dbManager.getPlayer(1);
        try {
            String mode = player.getCurrentMode();
            if (mode == null) {
                throw new NullPointerException();
            } else {
                modeView.setText(mode);
            }
        }
        catch (NullPointerException nullPointerException) {
            modeView.setText("No mode selected");
        }
        try {
            String difficulty = player.getDifficulty();
            if (difficulty == null) {
                throw new NullPointerException();
            } else {
                difficultyView.setText(difficulty);
            }
        }
        catch (NullPointerException nullPointerException) {
            difficultyView.setText("No difficulty selected");
        }
    }
    public void setMainApp(Gui mainApp) throws IOException {
        this.mainApp = mainApp;
        if (this.mainApp.mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING || this.mainApp.mediaPlayer.getMedia() != this.mainApp.media.get(0)) {
            this.mainApp.changeMusic("music", true);
            this.mainApp.mediaPlayer.setVolume(player.getVolume());
            this.mainApp.mediaPlayer.play();
        }
    }
    public void leaveGame(javafx.event.ActionEvent actionEvent) {
        mainApp.leaveConfirmatie.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK) {
                stage.close();
            }
        });
    }
    public void onButtonClick(javafx.event.ActionEvent actionEvent) throws IOException {
        if (Objects.equals(dbManager.getPlayer(1).getCurrentMode(), "OneVOne")) {
            stage = mainApp.getOneVOneController(stage);
            stage.show();
            mainApp.mediaPlayer.stop();
        } else if (Objects.equals(dbManager.getPlayer(1).getCurrentMode(), "Tournament")) {
            stage = mainApp.getTournament(stage);
            stage.show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Sorry, no game mode is currently selected, go into settings to set a mode").showAndWait();
        }
    }
    public void onSettingsClick(javafx.event.ActionEvent actionEvent) throws IOException {
        stage = mainApp.getSettingsScreen(stage);
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
