package com.sparkbigdata.tools;

import java.sql.*;

public class Connexion {
    private String DBPath = "tools/src/asset/db";
    private Connection connection = null;
    private Statement statement = null;

    public Connexion(String dbPath) {
        DBPath = dbPath;
    }

    public void connect() {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion à " + DBPath + "avec succès");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String request) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la request : " + request);
        }
        return resultat;

    }
}
