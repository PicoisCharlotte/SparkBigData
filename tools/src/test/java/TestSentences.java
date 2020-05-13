import com.sparkbigdata.tools.SparkSessionFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;

public class TestSentences {
    @Test
    public void testSentences() {
        Logger.getLogger("org").setLevel(Level.ERROR);
        Logger.getLogger("akka").setLevel(Level.ERROR);

        final SparkSession session = SparkSessionFactory.getSession();
        Dataset<Row> result = session.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .option("delimiter", ",")
                .csv(TestSentences.class.getClassLoader().getResource("sentences.csv").getFile());
        try {
            result.createTempView("SENTENCES");
            session.sql("SELECT PREVIOUS, CURRENT, COUNT(*) FROM SENTENCES GROUP BY PREVIOUS, CURRENT ORDER BY PREVIOUS, COUNT(*) DESC").show();
        } catch (AnalysisException e) {
            e.printStackTrace();
        }
    }
}
