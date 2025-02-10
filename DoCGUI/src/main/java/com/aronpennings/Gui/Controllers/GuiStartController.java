package com.aronpennings.Gui.Controllers;

import com.aronpennings.DuelofChampionsJavaEdition.DBManagement.DBManager;
import com.aronpennings.DuelofChampionsJavaEdition.Player.Player;
import com.aronpennings.Gui.Gui;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.sql.SQLException;

public class GuiStartController {
    public Button exitKnop;
    public TextField modeView;
    public TextField difficultyView;

    @FXML
    private Gui mainApp;
    private Stage stage;
    private DBManager dbManager;

    public GuiStartController() throws SQLException, ClassNotFoundException {
        dbManager = new DBManager();
    }

    public void initialize() {
        Player player = dbManager.getPlayer(1);
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
    public void setMainApp(Gui mainApp) {
        this.mainApp = mainApp;
        String audioFile = new File("DoCGUI/src/main/resources/Audio/music.mp3").toURI().toString();
        Media media = new Media(audioFile);
        if (this.mainApp.mediaPlayer == null) {
            this.mainApp.mediaPlayer = new MediaPlayer(media);
            this.mainApp.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            this.mainApp.mediaPlayer.setVolume(0.1);
            this.mainApp.mediaPlayer.play();
        }
        if (this.mainApp.mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
            this.mainApp.mediaPlayer.setVolume(0.1);
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
    public void onButtonClick(javafx.event.ActionEvent actionEvent) {
        mainApp.mediaPlayer.stop();
        if (dbManager.getPlayer(1).getCurrentMode() != null) {
            mainApp.launchOneVOne(stage);
        } else {
            new Alert(Alert.AlertType.ERROR, "Sorry, no game mode is currently selected, go into settings to set a mode").showAndWait();
        }
    }
    public void onSettingsClick(javafx.event.ActionEvent actionEvent) {
        mainApp.launchSettings(stage);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
