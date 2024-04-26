package com.aronpennings.DuelofChampionsJavaEdition;

public class Dataseeder {
    UI ui;
    public Dataseeder() {
        ui = new UI();
        ui.startMenu();
    }
    public void application() {
        ui.keuzeMenu();
    }
}
