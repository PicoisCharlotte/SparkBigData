import java.util.List;

public class Main {
    public static void main(String[] args) {
        String PATH = "C:\\Users\\lucil\\OneDrive\\Documents\\cours\\BigData\\SparkBigData\\reader\\src\\test\\resources\\corpus.txt";

        NGramsCsvExporter exporter = new NGramsCsvExporter();
        List<String> lines = null;
        try {
            lines = exporter.exportToLines(PATH,3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String previousLine = null ;
        for(String line : lines) {
            String[] words = line.split(",");
            if (words.length != 3) {
                System.out.println(line);
            } else {
                System.out.println(words.length);
            if (previousLine != null) {
                // last word of previous line equals the first word of current line
                String split = previousLine.split(",")[2];
                String split2 = words[1];
                System.out.println(split);
                System.out.println(split2);
            }

            for (int i = 0; i < words.length; i++) {
                System.out.println(words[i]);
            }
            System.out.println();


            previousLine = line ;
        }
    }
    }
}
