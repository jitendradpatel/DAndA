import scala.util.parsing.combinator.testing.Str;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by jpatel on 11/3/15.
 */
public class DataGenerator {

    public static void main(String[] args) throws Exception {
        emea();
    }

    public static void emea() throws Exception {
        Map<String, List<String>> mapdata = new HashMap<>();
        Map<String, String> mdata = new HashMap<>();
        Stream<String> lines = Files.lines(Paths.get("/Users/jpatel/temp/data2.txt"));
//        lines = lines.filter(left -> !left.trim().isEmpty());
//        lines.forEach(left -> {
//          String vals[] = left.split(",");
//            mdata.put(vals[1], vals[0]);
//        });
        lines = Files.lines(Paths.get("/Users/jpatel/temp/data3.txt"));
        lines = lines.filter(l -> !l.startsWith("###")).filter(l -> !l.trim().isEmpty()).filter(l -> !l.contains("TC.SITE_NAME")).filter(l -> !l.startsWith("--------"));
        //lines.forEach(left -> System.out.println(left));
        lines.forEach(l -> {
            String[] vals = l.split("#");
            String cmd = "mongo --port 15000 " + vals[0] + " --eval '" + vals[1] + "'";
            System.out.println(cmd);
            cmd = "mongo --port 15000 " + vals[0] + " --eval '" + vals[1].replace("message", "alerts") + "'";
            System.out.println(cmd);
            cmd = "mongo --port 15000 " + vals[0] + " --eval '" + vals[1].replace("message", "events") + "'";
            System.out.println(cmd);
//            String key = mdata.get(vals[0]);
//            if (mapdata.containsKey(key)) {
//                mapdata.get(key).add(cmd);
//            } else {
//                ArrayList<String> a = new ArrayList<String>();
//                a.add(cmd);
//                mapdata.put(key, a);
//            }

        });
        for (String k : mapdata.keySet()) {
            List<String> list = mapdata.get(k);
            //System.out.println(k);
            //for (String k1 : list)
            //System.out.println(k1);
        }
    }

    public static void na1() throws Exception {
        Map<String, List<String>> mapdata = new HashMap<>();
        Map<String, String> mdata = new HashMap<>();
        Stream<String> lines = Files.lines(Paths.get("/Users/jpatel/temp/data2.txt"));
        lines = lines.filter(l -> !l.trim().isEmpty());
        lines.forEach(l -> {
            String vals[] = l.split(",");
            mdata.put(vals[1], vals[0]);
        });
        lines = Files.lines(Paths.get("/Users/jpatel/temp/data1.txt"));
        lines = lines.filter(l -> !l.startsWith("###")).filter(l -> !l.trim().isEmpty()).filter(l -> !l.contains("TC.SITE_NAME"));
        //lines.forEach(left -> System.out.println(left));
        lines.forEach(l -> {
            String[] vals = l.split("#");
            String cmd = "mongo --port 15000 " + vals[0] + " --eval '" + vals[1] + "'";
            String key = mdata.get(vals[0]);
            if (mapdata.containsKey(key)) {
                mapdata.get(key).add(cmd);
            } else {
                ArrayList<String> a = new ArrayList<String>();
                a.add(vals[1]);
                mapdata.put(key, a);
            }

        });
        for (String k : mapdata.keySet()) {
            List<String> list = mapdata.get(k);
            System.out.println(k);
            for (String k1 : list)
                System.out.println(k1);
        }
    }
}
