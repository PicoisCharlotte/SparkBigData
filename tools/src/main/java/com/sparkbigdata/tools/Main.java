package com.sparkbigdata.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connexion connexion = new Connexion("Database.db");
        connexion.connect();

        ResultSet resultSet = connexion.query("SELECT * FROM BOOK");

        try {
            while (resultSet.next()) {
                System.out.println("Titre : "+resultSet.getString("Title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connexion.close();
    }


}
