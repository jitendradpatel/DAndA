package threads;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executor;

/**
 * Created by jpatel on 6/29/15.
 */
public class TestDBConnection {
    private static String driverClassName = "oracle.jdbc.driver.OracleDriver";
    private static String userName = "tp2";
    private static String password = "tp2";
    private static String url = "jdbc:oracle:thin:@192.168.10.101:1521:XE";
    private static BasicDataSource ds;

    public static void main(String[] args) throws Exception {
        Thread.currentThread().setName("MainThread");
        initConnectionPool();
        Connection conn = ds.getConnection();
        try {
            Thread t = new SMFThread(conn);
            t.start();
            t.setName("Thread-1");
            Thread.sleep(10000);
            System.out.println("Intrupting...");
            t.interrupt();
        } finally {
            conn.close();
        }
    }

    public static void initConnectionPool() throws Exception {
        ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setPassword(password);
        ds.setUsername(userName);
        ds.setUrl(url);
    }
}

class SMFThread extends Thread {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public SMFThread(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void interrupt() {
        System.out.println("Intrupted...");
        super.interrupt();
        try {
            ps.cancel();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ps = conn.prepareStatement("select site_name from tpt_config_info for update");
            //ps.setQueryTimeout(2);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                System.out.println("Result: " + rs.getString("site_name"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) ps.close();
                if(rs != null) rs.close();
            } catch (Exception ee) {
            }
        }
    }
}