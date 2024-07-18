package com.aronpennings.DuelofChampionsJavaEdition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class GuiStartup extends Application {
    private Dataseeder dataseeder = new Dataseeder();
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlUrl = getClass().getResource("/com/aronpennings/DuelofChampionsJavaEdition/GUI.fxml");
        if (fxmlUrl == null) {
            throw new IOException("FXML file not found");
        }
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        AnchorPane root = loader.load();

        // Set controller
        GuiController guiController = loader.getController();
        guiController.setMainApp(this);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Duel Of Champions");
        stage.show();
    }
}
