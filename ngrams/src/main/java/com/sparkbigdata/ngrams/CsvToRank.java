package com.sparkbigdata.ngrams;

import org.apache.spark.sql.*;

public class CsvToRank {

    /**
     * Given a CSV with col1 and col2, and 2grams in it,
     * calculate the base recommandation dataset
     * @param session the spark session to perform sql requests
     * @param path the path of the file
     * @return the dataset with col1,col2,counter,rank and localid
     */
    public static Dataset<Row> rank2(SparkSession session, String path) throws Exception {
        Dataset<Row> csv = session.read()
                .option("inferSchema","true")
                .option("delimiter",",")
                .option("header",true)
                .csv(path);
        csv.createTempView("RESTAURANT");
        Dataset<Row> counters = csv.sqlContext().sql("select NAME, count(*) as counter from RESTAURANT group by NAME order by NAME, COUNT(*) DESC");
        counters.createTempView("COUNTERS");
        Dataset<Row> orderedCouples = session
                .sql("select NAME,  counter , "
                        + " dense_rank() over (partition by NAME order by COUNTER desc ) as rank , "
                        + " row_number() over (partition by NAME  order by NAME, COUNTER desc, NAME ) as localid "
                        + " from COUNTERS "
                        + " order by NAME , rank asc , localid asc");
        return orderedCouples ;
    }
}
