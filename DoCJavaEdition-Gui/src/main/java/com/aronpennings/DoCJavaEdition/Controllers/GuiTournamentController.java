package com.aronpennings.DoCJavaEdition.Controllers;

import com.aronpennings.DoCJavaEdition.DBManagement.DBManager;
import com.aronpennings.DoCJavaEdition.Gui;
import com.aronpennings.DoCJavaEdition.Modes.Tournament;
import com.aronpennings.DoCJavaEdition.Player.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GuiTournamentController {
    public TextField botAmount;
    public TextField tournamentName;
    public Button startButton;
    public Button continueButton;
    public Button exitButton;
    private Stage stage;
    private Gui mainApp;
    private Tournament tournament;

    private DBManager dbManager;
    private Player currentPlayer;
    private int amountOfBots;
    private int tournamentCount;

    public GuiTournamentController() throws SQLException, ClassNotFoundException {
        dbManager = new DBManager();
        tournamentCount = 1;
    }

    public void initialize() {
        currentPlayer = dbManager.getPlayer(1);
        deactivateButton(continueButton);
    }

    public void startTournament(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        try {
            amountOfBots = Integer.parseInt(botAmount.getText());
            deactivateButton(startButton);
            tournament = new Tournament(amountOfBots - 1, currentPlayer, tournamentName.getText());
            tournamentName.setEditable(false);
            botAmount.setEditable(false);
            GuiOneVOneController controller = mainApp.getOneVOneController(new Stage(), tournament.getSingularNPC(0));
            Stage stageForFights = controller.getStage();
            stageForFights.showAndWait();
            activateButton(continueButton, this::continueTournament);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Sorry, Amount of bots needs to have a number").showAndWait();
        }
    }

    public void continueTournament(ActionEvent actionEvent) {
        try {
            if (!(tournamentCount == amountOfBots)) {
                GuiOneVOneController controller = mainApp.getOneVOneController(new Stage(), tournament.getSingularNPC(tournamentCount));
                Stage stageForFights = controller.getStage();
                deactivateButton(continueButton);
                deactivateButton(exitButton);
                stageForFights.showAndWait();
                if (controller.getMatch().getPlayer().getHealth() <= 0) {
                    activateButton(exitButton, this::leaveGame);
                } else {
                    tournamentCount++;
                    activateButton(continueButton, this::continueTournament);
                    activateButton(exitButton, this::leaveGame);
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Sorry, something went wrong: " + e.getLocalizedMessage()).showAndWait();
        }
    }

    public void leaveGame(ActionEvent e) {
        mainApp.leaveConfirmatie.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK) {
                try {
                    mainApp.start(stage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    private void activateButton(Button button, EventHandler<ActionEvent> eventHandler) {
        button.setOpacity(1);
        button.setCursor(Cursor.cursor("Hand"));
        button.setOnAction(eventHandler);
    }
    private void deactivateButton(Button button) {
        button.setOpacity(0);
        button.setOnAction(null);
        button.setCursor(Cursor.cursor("Default"));
    }
    public void setMainApp(Gui mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
