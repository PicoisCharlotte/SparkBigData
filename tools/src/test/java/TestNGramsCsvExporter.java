import com.sparkbigdata.tools.NGramsCsvExporter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class TestNGramsCsvExporter {
    /** path of the file that contains the text to read */
    private final static String PATH = TestCorpusReader.class.getClassLoader().getResource("test.txt").getFile();

    @Test(expected = IndexOutOfBoundsException.class)
    public void testBadParameters() throws Exception {
        NGramsCsvExporter exporter = new NGramsCsvExporter();
        exporter.exportToLines(PATH,0);
    }

    @Test
    public void testCsv() throws Exception {
        NGramsCsvExporter exporter = new NGramsCsvExporter();
        List<String> lines = exporter.exportToLines(PATH,3);
        String previousLine = null ;
        Iterator<String> iterator = lines.iterator();

        for(String line : lines) {
            String[] words = line.split(",");

            if(!iterator.hasNext()) {
                Assert.assertEquals("FINDUROMAN,,,",line);
            } else if(words.length == 3){
                Assert.assertEquals(3,words.length);
            }

            if (previousLine != null) {
                // last word of previous line equals the first word of current line
                Assert.assertEquals(previousLine.split(",")[1],words[0]);
            }

            testLines(line);

            previousLine = line ;
        }

    }

    public void testLines(String words) throws Exception {
        if(words.split(",").length > 2 ) {
            System.out.println("word 0: " + words.split(",")[0]);
            System.out.println("word 1 : " + words.split(",")[1]);
            System.out.println("word 2 : " + words.split(",")[2]);
        }
    }
}
