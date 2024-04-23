package com.aronpennings.DuelofChampionsJavaEdition;

public class Dataseeder {
    UI ui;
    public Dataseeder() {
        ui = new UI();
    }
    public void application() {
        System.out.println(ui.startMenu());
    }
}
