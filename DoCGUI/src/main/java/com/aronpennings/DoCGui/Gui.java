package com.aronpennings.DoCGui;

import com.aronpennings.DoCCore.DBManagement.DBManager;
import com.aronpennings.DoCGui.Controllers.GuiOneVOneController;
import com.aronpennings.DoCGui.Controllers.GuiSettingsController;
import com.aronpennings.DoCGui.Controllers.GuiStartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;


public class Gui extends Application {
    public DBManager dbManager;
    public MediaPlayer mediaPlayer;
    public ArrayList<Media> media;
    {
        try {
            dbManager = new DBManager();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Alert leaveConfirmatie = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to leave?");
    public Gui() {
        media = new ArrayList<>();
        media.add(new Media(new File("DoCGUI/src/main/resources/Audio/music.mp3").toURI().toString()));
        media.add(new Media(new File("DoCGUI/src/main/resources/Audio/beforestart.mp3").toURI().toString()));
        media.add(new Media(new File("DoCGUI/src/main/resources/Audio/Combat.mp3").toURI().toString()));
        media.add(new Media(new File("DoCGUI/src/main/resources/Audio/lost.mp3").toURI().toString()));
        media.add(new Media(new File("DoCGUI/src/main/resources/Audio/victory.mp3").toURI().toString()));
        media.add(new Media(new File("DoCGUI/src/main/resources/Audio/death.mp3").toURI().toString()));
        media.add(new Media(new File("DoCGUI/src/main/resources/Audio/playerlowhealth.mp3").toURI().toString()));
        media.add(new Media(new File("DoCGUI/src/main/resources/Audio/botlowhealth.mp3").toURI().toString()));
        mediaPlayer = new MediaPlayer(media.get(0));
        mediaPlayer.setVolume(0.1);
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException{
        launchScreen(stage);
    }
    public void changeMusic(String audioNaam, Boolean isLooped) throws IOException {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        Media goedmuziekje = null;
        for (int i = 0; i < media.size(); i++) {
            if (media.get(i).getSource().contains(audioNaam)) {
                goedmuziekje = media.get(i);
                break;
            }
        }
        if (goedmuziekje == null) {
            throw new IOException("Het opgegeven string is niet gevonden in de lijst met media");
        }
        mediaPlayer = new MediaPlayer(goedmuziekje);
        if (isLooped) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        } else {
            mediaPlayer.setCycleCount(0);
        }
        mediaPlayer.setVolume(0.1);
    }
    public void launchScreen(Stage stage) throws IOException{
        URL fxmlUrl = getClass().getResource("/com/aronpennings/DuelofChampionsJavaEdition/GUI.fxml");
        if (fxmlUrl == null) {
            throw new IOException("FXML file not found");
        }
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        AnchorPane root = loader.load();

        // Set controller
        GuiStartController guiStartController = loader.getController();
        guiStartController.setMainApp(this);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Duel Of Champions Launcher");
        guiStartController.setStage(stage);
        stage.show();
    }
    public void launchSettingsScreen(Stage stage) throws IOException{
        URL fxmlUrl = getClass().getResource("/com/aronpennings/DuelofChampionsJavaEdition/GUISettings.fxml");
        if (fxmlUrl == null) {
            throw new IOException("FXML file not found");
        }
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        AnchorPane root = loader.load();

        // Set controller
        GuiSettingsController guiSettingsController = loader.getController();
        guiSettingsController.setMainApp(this);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Duel Of Champions Settings");
        guiSettingsController.setStage(stage);
        stage.show();
    }
    public void launchOneVOneScreen(Stage stage) throws IOException {
        URL fxmlUrl = getClass().getResource("/com/aronpennings/DuelofChampionsJavaEdition/GUIOneVOne.fxml");
        if (fxmlUrl == null) {
            throw new IOException("FXML file not found");
        }
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        AnchorPane root = loader.load();

        // Set controller
        GuiOneVOneController guiOneVOneController = loader.getController();
        guiOneVOneController.setMainApp(this);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Duel Of Champions");
        guiOneVOneController.setStage(stage);
        stage.show();
    }

    public void launchOneVOne(Stage stage) {
        try {
            launchOneVOneScreen(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchSettings(Stage stage) {
        try {
            launchSettingsScreen(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

