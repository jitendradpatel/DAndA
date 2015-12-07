package threads;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.concurrent.Future;

/**
 * Created by jpatel on 9/22/15.
 */
public class ThreadTest2 {

    private static String driverClassName = "oracle.jdbc.driver.OracleDriver";
    private static String userName = "tp2";
    private static String password = "tp2";
    private static String url = "jdbc:oracle:thin:@192.168.10.101:1521:XE";
    private static BasicDataSource ds;

    public static void main(String[] args) throws Exception {
        initConnectionPool();
        JDBCTestThread thread = new JDBCTestThread(ds.getConnection());
        thread.start();
        Thread.sleep(1000);
        System.out.println("Calling stop...");
        thread.stop();
    }

    public static void initConnectionPool() throws Exception {
        ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setPassword(password);
        ds.setUsername(userName);
        ds.setUrl(url);
    }
}

class JDBCTestThread extends Thread {

    private Connection conn;

    public JDBCTestThread(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        System.out.println("Starting thread execution...");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select site_name from tpt_config_info for update");
            rs = ps.executeQuery();
            if (rs.next())
                System.out.println("Result: " + rs.getString("site_name"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                if(rs != null) rs.close();
            } catch (Exception e) {

            }
            System.out.println("Ended thread execution...");
        }
    }


}
