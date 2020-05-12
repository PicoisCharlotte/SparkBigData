import com.sparkbigdata.ngrams.CsvToRank;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Assert;
import org.junit.Test;
import com.sparkbigdata.tools.SparkSessionFactory;

public class TestCsvToRank {

    private final static String path = TestCsvToRank.class.getClassLoader().getResource("food.csv").getFile();

    @Test
    public void test2() throws Exception {
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);
        SparkSession testSession = SparkSessionFactory.getSession();
        Dataset<Row> rank = CsvToRank.rank2(testSession,path);
        for(Row row : rank.collectAsList()) {
            Assert.assertEquals(4,row.size());
        }
        rank.show();
    }
}
