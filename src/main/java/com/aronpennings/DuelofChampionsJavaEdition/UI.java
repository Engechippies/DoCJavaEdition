package com.aronpennings.DuelofChampionsJavaEdition;
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);

    public String startMenu() {
        String playerNaam;
        System.out.println("Welcome...\nSorry, I dont know your name, what is your name?\n");
        playerNaam = scanner.nextLine();
        System.out.println("Ah, okay. Welcome " + playerNaam + " in Duel Of Champions");
        System.out.println();
        return playerNaam;
    }
    public String keuzeMenu() {
        String keuzeInMenu = "";
        System.out.println("\nWhat do you want to do?\n1.Quick match\n2.Tournament\n3.Settings\n4.Quit");
        switch(scanner.nextInt()) {
            case 1:
                keuzeInMenu = "onevone";
                break;
            case 2:
                keuzeInMenu = "tournament";
                break;
            case 3:
                keuzeInMenu = "settings";
                break;
            case 4:
                System.exit(0);
            default:
                break;
        }
        return keuzeInMenu;
    }
}
