package com.aronpennings.DoCGui.Controllers;

import com.aronpennings.DoCCore.Modes.Settings;
import com.aronpennings.DoCCore.Player.Player;
import com.aronpennings.DoCGui.Gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class GuiSettingsController {
    public TextField cheatInput; //cheats yet to be implemented
    public TextField critchanceInpuit;
    public TextField healthInput;
    public TextField speedInput;
    public TextField damageInput;
    public TextField naamInput;
    public ChoiceBox difficultyBox;
    public ChoiceBox modeBox;
    public Slider audioSlider;
    @FXML
    private Gui mainApp;
    private Stage stage;
    private Settings settings;
    private Player player;

    public GuiSettingsController() {
        settings = new Settings();
        player = settings.getPlayer();
    }

    public void initialize() {
        naamInput.setText(player.getName());
        damageInput.setText(Objects.toString(player.getDamage()));
        speedInput.setText(Objects.toString(player.getSpeed()));
        healthInput.setText(Objects.toString(player.getHealth()));
        critchanceInpuit.setText(Objects.toString(player.getCritchance()));

        audioSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mainApp.mediaPlayer.setVolume((Double) newValue / 1000);
        });
        ObservableList<String> observableList = FXCollections.observableArrayList(settings.giveDifficultyInformationToGui());
        difficultyBox.setItems(observableList);
        difficultyBox.setValue(player.getDifficulty());
        difficultyBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                settings.setDifficulty(newValue.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        ObservableList<String> observableList2 = FXCollections.observableArrayList(new String[]{"OneVOne", "Tournament"});
        modeBox.setItems(observableList2);
        modeBox.setValue(player.getCurrentMode());
            modeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    settings.setMode(newValue.toString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
    }
    public void setMainApp(Gui mainApp) {
        this.mainApp = mainApp;
        audioSlider.setValue(mainApp.mediaPlayer.getVolume() * 1000);
    }
    public void leaveGame(javafx.event.ActionEvent actionEvent) throws IOException {
        setCombatValues();
        setName();
        mainApp.start(stage);
    }
    public void setCombatValues() {
        try {
            int damage = Integer.parseInt(damageInput.getText());
            int speed = Integer.parseInt(speedInput.getText());
            int hp = Integer.parseInt(healthInput.getText());
            int critchance = Integer.parseInt(critchanceInpuit.getText());
            if (damage > 5 || speed > 5 || hp > 10 || critchance > 5 || (damage + speed + hp + critchance) > 18) {
                throw new Exception("no numbers > 5(except hp), all numbers added up should be no more than 18");
            }
            settings.setStats(1, damage, speed, hp, critchance);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry, something went wrong: " + e.getLocalizedMessage());
            System.out.println("Error: " + e);
            alert.showAndWait();
        }
    }
    public void setName() {
        try {
            String naam = naamInput.getText();
            if (naam.isEmpty()) {
                throw new Exception("Name cannot be empty");
            }
            if (naam.equalsIgnoreCase("exit")) {
                stage.close();
                throw new Exception("Application exited :)");
            }
            settings.setNaam(naam);
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry, something went wrong: " + e.getLocalizedMessage());
            alert.showAndWait();
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
