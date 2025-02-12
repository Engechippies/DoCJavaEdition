package com.aronpennings.DoCCore.Modes;

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
    @Column(name = "Difficulty")
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

    public Boolean getDidPlayerWin() {
        return didPlayerWin;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
