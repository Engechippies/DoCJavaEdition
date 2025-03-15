package com.aronpennings.DoCJavaEdition.Modes;

import jakarta.persistence.*;

@Entity
@Table(name = "Tournament")
public class TournamentDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name = "tournamentName")
    private String tournamentName;
    @Column(name = "didPlayerWin")
    private Boolean didPlayerWin;
    @Column(name = "nameUserWon")
    private String nameUserWon;
    @Column(name = "difficulty")
    private String difficulty;

    public TournamentDB(String tournamentName, Boolean didPlayerWin, String nameUserWon, String difficulty) {
        this.tournamentName = tournamentName;
        this.didPlayerWin = didPlayerWin;
        this.nameUserWon = nameUserWon;
        this.difficulty = difficulty;
    }

    public int getId() {
        return Id;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getNameUserWon() {
        return nameUserWon;
    }

    public void setNameUserWon(String nameUserWon) {
        this.nameUserWon = nameUserWon;
    }

    public Boolean getDidPlayerWin() {
        return didPlayerWin;
    }

    public void setDidPlayerWin(Boolean didPlayerWin) {
        this.didPlayerWin = didPlayerWin;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
