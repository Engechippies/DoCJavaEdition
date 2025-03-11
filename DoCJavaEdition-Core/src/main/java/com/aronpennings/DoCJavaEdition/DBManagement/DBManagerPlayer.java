package com.aronpennings.DoCJavaEdition.DBManagement;

import com.aronpennings.DoCJavaEdition.Player.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

public class DBManagerPlayer {
    public Player getPlayer(int id, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        return session.get(Player.class, 1);
    }
    public void ChangeDifficulty(String difficulty, SessionFactory sessionFactory) throws SQLException {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Player player = session.get(Player.class, 1);
        player.setDifficulty(difficulty);
        session.getTransaction().commit();
    }
    public void ChangeNaam(String naam, SessionFactory sessionFactory) throws SQLException {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Player player = session.get(Player.class, 1);
        player.setNaam(naam);
        session.getTransaction().commit();
    }
    public void ChangeDamage(int dmg, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Player player = session.get(Player.class, 1);
        player.setDamage(dmg);
        session.getTransaction().commit();
    }
    public void ChangeSpeed(int speed, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Player player = session.get(Player.class, 1);
        player.setSpeed(speed);
        session.getTransaction().commit();
    }
    public void ChangeHp(int hp, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Player player = session.get(Player.class, 1);
        player.setHp(hp);
        session.getTransaction().commit();
    }
    public void ChangeCritChance(int critChance, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Player player = session.get(Player.class, 1);
        player.setCritchance(critChance);
        session.getTransaction().commit();
    }
    public void ChangeVolume(double volume, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Player player = session.get(Player.class, 1);
        player.setVolume(volume);
        session.getTransaction().commit();
    }
}
