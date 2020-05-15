package com.sparkbigdata.tools;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Query {
    private final static String PATH = Query.class.getClassLoader().getResource("corpus.txt").getFile();
    private final static String PATH2 = Query.class.getClassLoader().getResource("corpus2.txt").getFile();

    public static void main(String[] args) {
        Connexion connexion = new Connexion("tools/src/asset/db/Database.db");
        connexion.connect();


        //clean2GramsTable(connexion);
        //clean3GramsTable(connexion);

        try {
            insert2grams(connexion, PATH);
            insert2grams(connexion, PATH2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            insert3grams(connexion, PATH);
            insert3grams(connexion, PATH2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        connexion.close();
    }


    public static void insert3grams(Connexion connexion, String path) throws Exception {
        String previousWord1 = "";
        String previousWord2 = "";
        String currentWord = "";

        NGramsCsvExporter exporter = new NGramsCsvExporter();
        List<String> lines = exporter.exportToLines(path,3);

        for(String line: lines) {

            if(line.split(",").length > 2 ) {
                previousWord1 = line.split(",")[0];
                previousWord2 = line.split(",")[1];
                currentWord = line.split(",")[2];
            }

            connexion.execQuery("INSERT INTO '3GRAMS' (PREVIOUS1,PREVIOUS2,CURRENT) VALUES ('"
                    + new String(previousWord1.getBytes(), StandardCharsets.UTF_8) + "','"
                    +  new String(previousWord2.getBytes(), StandardCharsets.UTF_8) + "','"
                    +  new String(currentWord.getBytes(), StandardCharsets.UTF_8) + "')");
        }
    }

    public static void insert2grams(Connexion connexion, String path) throws Exception {
        String previousWord = "";
        String currentWord = "";

        NGramsCsvExporter exporter = new NGramsCsvExporter();
        List<String> lines = exporter.exportToLines(path,2);

        for(String line: lines) {

            if(line.split(",").length > 1 ) {
                previousWord = line.split(",")[0];
                currentWord = line.split(",")[1];
            }

            connexion.execQuery("INSERT INTO '2GRAMS' (PREVIOUS,CURRENT) VALUES ('"
                    + new String(previousWord.getBytes(), StandardCharsets.UTF_8) + "','"
                    + new String(currentWord.getBytes(), StandardCharsets.UTF_8) + "')");
        }
    }

    public static void clean3GramsTable(Connexion connexion) {
        connexion.execQuery("DELETE FROM '3GRAMS'");
    }
    public static void clean2GramsTable(Connexion connexion) {
        connexion.execQuery("DELETE FROM '2GRAMS'");
    }
}
