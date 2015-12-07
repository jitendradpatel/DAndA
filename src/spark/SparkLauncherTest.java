package spark;

import org.apache.spark.launcher.SparkLauncher;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jpatel on 10/13/15.
 */
public class SparkLauncherTest {
    public static void main(String[] args) throws Exception {
        Process spark = new SparkLauncher()
                .setSparkHome("/Users/jpatel/sabatools/spark-1.5.1-bin-hadoop2.6")
                .setAppResource("/Users/jpatel/sabatools/spark-1.5.1-bin-hadoop2.6/work/javasparktest.jar")
                .setMainClass("spark.TestSpark")
                .setMaster("spark://rws-jpatel.local:7077")
                .launch();

        BufferedReader reader = new BufferedReader (new InputStreamReader(spark.getInputStream()));
        String line = null;
        while ((line = reader.readLine ()) != null) {
            System.out.println ("Stdout: " + line);
        }
        spark.waitFor();
        System.out.println("Done!!!");
    }
}
