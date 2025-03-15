package com.aronpennings.DoCJavaEdition.DBManagement;

import com.aronpennings.DoCJavaEdition.Modes.TournamentDB;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class DBManagerTournament {
    public ArrayList<TournamentDB> getTournaments(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        ArrayList<TournamentDB> tournaments = new ArrayList<>();
        try {
            int amountOfTournaments = (int) session.createQuery("select count(Id) from Tournaments").getSingleResult();
            for(int i = 1; i <= amountOfTournaments; i++) {
                tournaments.add(session.get(TournamentDB.class, i));
            }
        } catch (Exception e) {
            return null;
        }
        return tournaments;
    }
    public void setTournament(SessionFactory sessionFactory, String tournamentName, Boolean didPlayerWin, String nameUserWon, String difficulty) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(new TournamentDB(tournamentName, didPlayerWin, nameUserWon, difficulty));
        session.getTransaction().commit();
    }
    public void changeTournament(SessionFactory sessionFactory, String tournamentName, Boolean didPlayerWin, String nameUserWon, int tournamentId) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        TournamentDB tournament = session.get(TournamentDB.class, tournamentId);
        if (tournamentName != null) {
            tournament.setTournamentName(tournamentName);
        }
        if (didPlayerWin != null) {
            tournament.setDidPlayerWin(didPlayerWin);
        }
        if (nameUserWon != null) {
            tournament.setNameUserWon(nameUserWon);
        }
        session.getTransaction().commit();
    }
}
