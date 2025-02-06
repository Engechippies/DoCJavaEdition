package com.aronpennings.Gui;

import com.aronpennings.DuelofChampionsJavaEdition.DBManagement.DBManager;
import com.aronpennings.Gui.Controllers.GuiOneVOneController;
import com.aronpennings.Gui.Controllers.GuiSettingsController;
import com.aronpennings.Gui.Controllers.GuiStartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


public class Gui extends Application {
    public DBManager dbManager;
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
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException{
        launchScreen(stage);
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

