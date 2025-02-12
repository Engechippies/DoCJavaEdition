package com.aronpennings.DoCGui.Controllers;

import com.aronpennings.DoCCore.Modes.OneVOne;
import com.aronpennings.DoCCore.Player.NPC;
import com.aronpennings.DoCCore.Player.NPCStrings.NPCStrings;
import com.aronpennings.DoCCore.Player.Player;
import com.aronpennings.DoCGui.Gui;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

public class GuiOneVOneController {
    public Button exitKnop;
    public Button returnToLauncher;
    public TextArea textScreen;
    public Button startButton;
    public Button nothingButton;
    public Button healButton;
    public Button blockButton;
    public Button attackButton;
    public Button rematchButton;
    public Button returnToLauncher1;
    public AnchorPane background;

    @FXML
    private Gui mainApp;
    private Stage stage;
    private NPC npc;
    private Player player;
    private OneVOne match;
    private int botOriginalHealth;
    private final Map<String, Consumer<String>> commands = new HashMap<>();
    
    private Timeline timelineGameLoop = new Timeline(
            new KeyFrame(Duration.seconds(0), e -> addTextToScreen(npc.getName() + "'s turn:")),
            new KeyFrame(Duration.seconds(1.5), e -> NPCTurn()),
            new KeyFrame(Duration.seconds(1.8), e -> {
                addTextToScreen("\n" + player.getName() + "'s turn");
            }),
            new KeyFrame(Duration.seconds(3), e -> {
                Consumer<String> action = commands.get(player.getMove().toLowerCase());
                if (action != null) {
                    action.accept(player.getMove());
                    player.setMove("nothing");
                }
            }),
            new KeyFrame(Duration.seconds(3.5), e -> {
                addTextToScreen("\n\n" + npc.getName() + "\nhp: " + npc.getHealth() + "\n\n" + player.getName() + "\nhp: " + player.getHealth());
                ActivateButtons(new ActionEvent());
            })
    );
    private Timeline timelineGameEnd = new Timeline(
            new KeyFrame(Duration.seconds(0), e -> {
                try {
                    mainApp.changeMusic("death", true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                mainApp.mediaPlayer.play();
            }),
            new KeyFrame(Duration.seconds(1.5), e -> {
                if (player.getHealth() <= 0) {
                    addTextToScreen("Your hp:");
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.seconds(1), action -> addTextToScreen(String.valueOf(player.getHealth()))),
                            new KeyFrame(Duration.seconds(3), action -> {
                                try {
                                    defeat();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            })
                    );
                    timeline.play();

                } else {
                    addTextToScreen(npc.getName() + "'s hp:");
                    Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(1), action -> addTextToScreen(String.valueOf(npc.getHealth()))),
                        new KeyFrame(Duration.seconds(3), action -> {
                            try {
                                victory();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                    );
                    timeline.play();
                }
            })
    );

    public GuiOneVOneController() throws SQLException, ClassNotFoundException {
        match = new OneVOne();
        commands.put("attack", this::attackAction);
        commands.put("block", this::blockAction);
        commands.put("heal", this::healAction);
        commands.put("nothing", this::doNothingAction);
        npc = match.getNPC();
        player = match.getPlayer();
        botOriginalHealth = npc.getHealth();
    }

    public void startGame(javafx.event.ActionEvent actionEvent) throws IOException {
        mainApp.changeMusic("Combat", true);
        mainApp.mediaPlayer.play();
        DeactivateButton(startButton);
        String startSentence = NPCStrings.NPC_STRINGS.getBeginSentences();
        String startSentenceResponse = NPCStrings.NPC_STRINGS.getBeginSentencesRespone();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> textScreen.setText(npc.getNaam() +startSentence + "\n")),
                new KeyFrame(Duration.seconds(3), e -> textScreen.setText(npc.getNaam() +startSentence + "\n" + player.getName() + ": I'm gonna absolutely smash you mate")),
                new KeyFrame(Duration.seconds(6), e -> textScreen.setText(npc.getNaam() +startSentence + "\n" + player.getName() + ": I'm gonna absolutely smash you mate\n" + npc.getNaam() + startSentenceResponse + "\n")),
                new KeyFrame(Duration.seconds(10), e -> addTextToScreen("3")),
                new KeyFrame(Duration.seconds(10.8), e -> addTextToScreen("2")),
                new KeyFrame(Duration.seconds(11.6), e -> addTextToScreen("1")),
                new KeyFrame(Duration.seconds(11.8), e -> addTextToScreen("Fight")),
                new KeyFrame(Duration.seconds(12.4), e -> textScreen.setText("Make your move, " + player.getName() + "\n")),
                new KeyFrame(Duration.seconds(12.6), e -> addTextToScreen("\n\n" + npc.getName() + "\nhp: " + npc.getHealth() + "\n\n" + player.getName() + "\nhp: "+player.getHealth()))
        );
        timeline.setOnFinished(this::ActivateButtons);
        timeline.play();
        addTextToScreen("\n\n\n" + npc.getName() + "\nhp: " + npc.getHealth() + "\n\n" + player.getName() + "\nhp: "+player.getHealth());
    }
    public void attackAction(String keyWord) {
        int damage = match.Attack(player, npc);
        npc.setHealth(npc.getHealth() - damage); //Achievements is een mooie feature om toe te voegen
        if (damage > 0) {
            addTextToScreen(player.getName() + " has done " + damage + " damage");
            if (npc.getHealth() <= 0) {
                timelineGameLoop.pause();
                timelineGameEnd.play();
            }
        } else {
            addTextToScreen(npc.getName() + " has evaded the attack of " + player.getName());
        }
    }
    public void blockAction(String keyWord) {
        player.setMove(match.Block());
        addTextToScreen("You have put up your defenses!");
    }
    public void healAction(String keyWord) {
        int hpAdded = match.Heal();
        player.setHealth(player.getHealth() + hpAdded);
        addTextToScreen(player.getName() + " has healed, an additional " + hpAdded + " hp has been added");
    }
    public void doNothingAction(String keyWord) {
        System.out.println("nope");
    }
    public void attack(javafx.event.ActionEvent actionEvent) {
        player.setMove("attack");
        gameplayLoop();
    }
    public void block(javafx.event.ActionEvent actionEvent) {
        player.setMove("block");
        gameplayLoop();
    }
    public void heal(javafx.event.ActionEvent actionEvent) {
        player.setMove("heal");
        gameplayLoop();
    }
    public void doNothing(javafx.event.ActionEvent actionEvent) {
        player.setMove("nothing");
        gameplayLoop();
    }
    public void addTextToScreen(String text) {
        String existingtext = textScreen.getText();
        existingtext += "\n";
        textScreen.setText(existingtext + text);
    }

    public boolean NPCTurn() {
        Random random = new Random();
        int bChoice = random.nextInt(5);
        if (bChoice < 3) {
            int damage = match.Attack(npc, player);
            player.setHealth(player.getHealth() - damage);
            if (damage > 0) {
                addTextToScreen(npc.getName() + " has done " + damage + " damage");
                if (player.getHealth() <= 0) {
                    timelineGameLoop.pause();
                    timelineGameEnd.play();
                }
            } else {
                addTextToScreen(player.getName() + " has evaded the attack of " + npc.getName());
            }
        } else if (bChoice == 3) {
            npc.setMove(match.Block());
            addTextToScreen(npc.getName() + " has put up their defenses!");
        } else {
            int hpAdded = match.Heal();
            npc.setHealth(npc.getHealth() + hpAdded);
            addTextToScreen(npc.getName() + " has healed, an additional " + hpAdded + " hp has been added");
        }
        return false;
    }
    public void defeat() throws IOException {
        mainApp.changeMusic("lost", true);
        mainApp.mediaPlayer.play();
        DeactivateButton(returnToLauncher);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> textScreen.setText("You have Lost...")),
                new KeyFrame(Duration.seconds(2.5), e -> addTextToScreen("As you lay there, you hear " + npc.getName() + " approaching")),
                new KeyFrame(Duration.seconds(6), e -> addTextToScreen(npc.getName() + NPCStrings.NPC_STRINGS.getEndSentencesLoss())),
                new KeyFrame(Duration.seconds(10))
        );
        timeline.setOnFinished(e -> {
            try {
                leaveGameNotForButton(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        timeline.play();
    }

    public void victory() throws IOException {
        mainApp.changeMusic("victory", true);
        mainApp.mediaPlayer.play();
        DeactivateButton(returnToLauncher);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> textScreen.setText("You have Won!")),
                new KeyFrame(Duration.seconds(2.5), e -> addTextToScreen("Before losing conciousness, " + npc.getName() + " mutters")),
                new KeyFrame(Duration.seconds(6), e -> addTextToScreen(npc.getName() + NPCStrings.NPC_STRINGS.getEndSentencesVictory())),
                new KeyFrame(Duration.seconds(10))
        );
        timeline.setOnFinished(e -> {
            try {
                leaveGameNotForButton(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        timeline.play();
    }

    public void gameplayLoop() {
        DeactivateButtons();
        npc.setMove("nothing");
        textScreen.setText("Make your move, " + player.getName() + "\n");
        timelineGameLoop.play();
    }

    private void ActivateButton(Button button, EventHandler<ActionEvent> eventHandler) {
        button.setOpacity(1);
        button.setCursor(Cursor.cursor("Hand"));
        button.setOnAction(eventHandler);
    }
    private void ActivateButtons(ActionEvent e) {
        ActivateButton(attackButton, this::attack);
        ActivateButton(blockButton, this::block);
        ActivateButton(healButton, this::heal);
        ActivateButton(nothingButton, this::doNothing);
    }
    private void DeactivateButtons() {
        DeactivateButton(attackButton);
        DeactivateButton(blockButton);
        DeactivateButton(healButton);
        DeactivateButton(nothingButton);
    }
    private void DeactivateButton(Button button) {
        button.setOpacity(0);
        button.setOnAction(null);
        button.setCursor(Cursor.cursor("Default"));
    }
    public void setMainApp(Gui mainApp) {
        this.mainApp = mainApp;
        try {
            mainApp.changeMusic("beforestart", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.mainApp.mediaPlayer.play();
    }
    public void leaveGameNotForButton(ActionEvent e) throws IOException {
        ActivateButton(rematchButton, this::rematch);
        ActivateButton(returnToLauncher1, this::leaveGame);
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
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void rematch(ActionEvent e) {
        npc.setHealth(botOriginalHealth);
        player = match.refreshPlayer();
        textScreen.setText("");
        DeactivateButton(rematchButton);
        DeactivateButton(returnToLauncher1);
        try {
            mainApp.changeMusic("beforestart", true);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        mainApp.mediaPlayer.play();
        ActivateButton(startButton, actionEvent -> {
            try {
                startGame(actionEvent);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
