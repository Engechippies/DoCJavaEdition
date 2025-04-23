package com.aronpennings.DoCJavaEdition;

import com.aronpennings.DoCJavaEdition.Controllers.GuiTournamentController;
import com.aronpennings.DoCJavaEdition.DBManagement.DBManager;
import com.aronpennings.DoCJavaEdition.Controllers.GuiOneVOneController;
import com.aronpennings.DoCJavaEdition.Controllers.GuiSettingsController;
import com.aronpennings.DoCJavaEdition.Controllers.GuiStartController;
import com.aronpennings.DoCJavaEdition.Player.NPC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

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
        try {
            media.add(new Media(getClass().getResource("/Audio/music.mp3").toExternalForm()));
            media.add(new Media(getClass().getResource("/Audio/beforestart.mp3").toExternalForm()));
            media.add(new Media(getClass().getResource("/Audio/Combat.mp3").toExternalForm()));
            media.add(new Media(getClass().getResource("/Audio/lost.mp3").toExternalForm()));
            media.add(new Media(getClass().getResource("/Audio/victory.mp3").toExternalForm()));
            media.add(new Media(getClass().getResource("/Audio/death.mp3").toExternalForm()));
            media.add(new Media(getClass().getResource("/Audio/playerlowhealth.mp3").toExternalForm()));
            media.add(new Media(getClass().getResource("/Audio/botlowhealth.mp3").toExternalForm()));
            mediaPlayer = new MediaPlayer(media.get(0));
            mediaPlayer.setVolume(0.1);
        } catch (Exception e) {
            System.err.println("Failed to initialize media player: " + e.getMessage());
            mediaPlayer = null;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException{
        stage = getlaunchScreen(stage);
        stage.show();
    }
    public void changeMusic(String audioNaam, Boolean isLooped) throws IOException {
        if (mediaPlayer == null) {
            System.err.println("MediaPlayer is not initialized");
            return;
        }
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
        mediaPlayer.setVolume(dbManager.getPlayer(1).getVolume());
    }
    public boolean playerIsWorking() {
        return mediaPlayer != null;
    }
    public boolean playerIsEmptyAndWorking() {
        if (!playerIsWorking()) {
            return false;
        }
        return mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING || mediaPlayer.getMedia() != media.get(0);
    }
    public Stage getlaunchScreen(Stage stage) throws IOException{
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
        stage.setTitle("DoC Launcher");
        guiStartController.setStage(stage);
        return stage;
    }
    public Stage getSettingsScreen(Stage stage) throws IOException{
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
        stage.setTitle("DoC Settings");
        guiSettingsController.setStage(stage);
        return stage;
    }
    public GuiOneVOneController getOneVOneController(Stage stage, NPC npc) throws IOException {
        URL fxmlUrl = getClass().getResource("/com/aronpennings/DuelofChampionsJavaEdition/GUIOneVOne.fxml");
        if (fxmlUrl == null) {
            throw new IOException("FXML file not found");
        }
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        AnchorPane root = loader.load();

        // Set controller
        GuiOneVOneController guiOneVOneController = loader.getController();
        guiOneVOneController.setMainApp(this);

        if (npc != null) {
            guiOneVOneController.changeNpcForTournament(npc);
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Duel Of Champions");
        guiOneVOneController.setStage(stage);
        return guiOneVOneController;
    }

    public Stage getOneVOneController(Stage stage) throws IOException {
        return getOneVOneController(stage, null).getStage();
    }

    public Stage getTournament(Stage stage) throws IOException {
        URL fxmlUrl = getClass().getResource("/com/aronpennings/DuelofChampionsJavaEdition/GUITournament.fxml");
        if (fxmlUrl == null) {
            throw new IOException("FXML file not found");
        }
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        AnchorPane root = loader.load();

        // Set controller
        GuiTournamentController guiTournamentController = loader.getController();
        guiTournamentController.setMainApp(this);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("DoC Tournament Selection");
        guiTournamentController.setStage(stage);
        return stage;
    }
}

