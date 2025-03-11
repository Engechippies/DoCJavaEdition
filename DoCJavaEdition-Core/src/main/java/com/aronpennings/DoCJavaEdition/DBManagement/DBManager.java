package com.aronpennings.DoCJavaEdition.DBManagement;
import com.aronpennings.DoCJavaEdition.Player.NPC;
import com.aronpennings.DoCJavaEdition.Player.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;

public class DBManager {
    private SessionFactory sessionFactory;

    public DBManager() throws ClassNotFoundException, SQLException {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        MetadataSources sources = new MetadataSources(standardRegistry);
        sources.addAnnotatedClass(Player.class);
        Metadata metadata = sources.buildMetadata();

        sessionFactory = metadata.buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Player playertest = session.get(Player.class, 1);
            if (playertest == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            Player player = new Player("?", 0, 0, 0, 0, "Normal", 0, "OneVOne", 1);
            session.getTransaction().begin();
            session.persist(player);
            session.getTransaction().commit();
        }
    }

    public String getCurrentMode() throws SQLException {
        Session session = sessionFactory.openSession();
        return session.get(Player.class, 1).getCurrentMode();
    }
    public void setCurrentMode(String mode) throws SQLException {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.get(Player.class, 1).setCurrentMode(mode);
        session.getTransaction().commit();
    }
    public NPC getRandomBot() throws SQLException {return new DBManagerBot().getRandomBot(sessionFactory);}
    public Player getPlayer(int id) {return new DBManagerPlayer().getPlayer(id, sessionFactory);}
    public void ChangeDifficulty(String difficulty) throws SQLException {new DBManagerPlayer().ChangeDifficulty(difficulty, sessionFactory);}
    public void ChangeNaam(String naam) throws SQLException {new DBManagerPlayer().ChangeNaam(naam, sessionFactory);}
    public void ChangeDamage(int dmg) {new DBManagerPlayer().ChangeDamage(dmg, sessionFactory);}
    public void ChangeSpeed(int speed) {new DBManagerPlayer().ChangeSpeed(speed, sessionFactory);}
    public void ChangeHp(int hp) {new DBManagerPlayer().ChangeHp(hp, sessionFactory);}
    public void ChangeCritChance(int critchance) {new DBManagerPlayer().ChangeCritChance(critchance, sessionFactory);}
    public void ChangeVolume(double volume) {new DBManagerPlayer().ChangeVolume(volume, sessionFactory);}
}