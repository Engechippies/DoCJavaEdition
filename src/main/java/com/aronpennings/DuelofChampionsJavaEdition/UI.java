package com.aronpennings.DuelofChampionsJavaEdition;
import java.util.Scanner;

public class UI {
    private String playerNaam;
    Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        System.out.println("Welcome...\nSorry, I dont know your name, what is your name?\n");
        playerNaam = scanner.nextLine();
        System.out.println("Ah, okay. Welcome " + playerNaam + " in Duel Of Champions");
        System.out.println();
    }
    public String keuzeMenu() {
        String keuzeInMenu = "";
        System.out.println("\nWhat do you want to do?\n1.Quick match\n2.Tournament\n3.Settings\n4.Quit");
        switch(scanner.nextInt()) {
            case 1:
                OneVOne oneVOne = new OneVOne();
            case 2:
                Tournament tournament = new Tournament();
            case 3:
                Settings settings = new Settings();
            case 4:
                System.exit(0);
            default:
                break;
        }
        return keuzeInMenu;
    }

    public String getPlayerNaam() {
        return playerNaam;
    }

    public void setPlayerNaam(String playerNaam) {
        this.playerNaam = playerNaam;
    }
}
