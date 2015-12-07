package spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Created by jpatel on 10/9/15.
 */
public class TestSpark {
    public static void main(String[] args) throws Exception {
        String master = "spark://rws-jpatel.local:7077"; //set IP address to that of your master
        String appName = "Spark Java Test";
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master).setJars(new String[] {"/Users/jpatel/sabatools/spark-1.5.1-bin-hadoop2.6/work/javasparktest.jar"});
        JavaSparkContext sc = new JavaSparkContext();
        System.out.println("*************Here is the Output**************");
        System.out.println(sc.getConf());
        System.out.println("**************DONE*********************");
    }
}
