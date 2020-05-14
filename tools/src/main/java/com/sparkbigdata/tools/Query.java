package com.sparkbigdata.tools;

import javax.xml.transform.Result;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import com.sparkbigdata.*;

public class Query {
    private final static String PATH = Query.class.getClassLoader().getResource("corpus.txt").getFile();

    public static void main(String[] args) {
        Connexion connexion = new Connexion("tools/src/asset/db/Database.db");
        connexion.connect();


//        ResultSet resultSet = connexion.query("SELECT * FROM 2GRAMS");

        cleanTable(connexion);

        try {
            insert3grams(connexion);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            while (resultSet.next()) {
//                System.out.println("Titre : "+resultSet.getString("PREVIOUS"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        connexion.close();
    }


    public static void insert3grams(Connexion connexion) throws Exception {
        String previousWord1 = "";
        String previousWord2 = "";
        String currentWord = "";

        NGramsCsvExporter exporter = new NGramsCsvExporter();
        List<String> lines = exporter.exportToLines(PATH,3);

        for(String line: lines) {

            if(line.split(",").length > 2 ) {
                previousWord1 = line.split(",")[0];
                previousWord2 = line.split(",")[1];
                currentWord = line.split(",")[2];
            }

            connexion.execQuery("INSERT INTO '3GRAMS' (PREVIOUS1,PREVIOUS2,CURRENT) VALUES ('"
                    + new String(previousWord1.getBytes(), Charset.defaultCharset()) + "','"
                    +  new String(previousWord2.getBytes(), Charset.defaultCharset()) + "','"
                    +  new String(currentWord.getBytes(), Charset.defaultCharset()) + "')");
        }


    }

    public static void cleanTable(Connexion connexion) {
        connexion.execQuery("DELETE FROM '3GRAMS'");
    }
}
