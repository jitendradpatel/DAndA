package threads;

import org.apache.commons.dbcp2.BasicDataSource;
import sun.nio.ch.Interruptible;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

/**
 * Created by jpatel on 9/17/15.
 */
public class ThreadExecutorTest {
    private static String driverClassName = "oracle.jdbc.driver.OracleDriver";
    private static String userName = "tp2";
    private static String password = "tp2";
    private static String url = "jdbc:oracle:thin:@192.168.10.101:1521:XE";
    private static BasicDataSource ds;

    public static class SingleThread extends Thread {
        private ThreadLocal<PreparedStatement> threadLocal = new ThreadLocal<PreparedStatement>();

        @Override
        public void interrupt() {
            System.out.println("Interrupt received.........");
            System.out.println("PS: " + threadLocal.get());
            try {
            }catch (Exception e) {
                System.out.println("error canceling..."+ e.getMessage());
            }
            super.interrupt();
        }

        public ThreadLocal<PreparedStatement> getThreadLocal() {
            return threadLocal;
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new SingleThread();
            }
        });

        initConnectionPool();
        Connection conn = ds.getConnection();
        try {
            System.out.println("Starting...");
            JobExectuor je = new JobExectuor(conn);
            Future<String> task = es.submit(je);
            MonitorThread mt = new MonitorThread(task);
            mt.start();
            System.out.println("Before get...");
            System.out.println(task.get());
            System.out.println("d: " + task.isDone());
            System.out.println("c: " + task.isCancelled());
            System.out.println("After get...");
        } catch (Exception e) {
            throw e;
        } finally {
            Thread.sleep(1000);
            if(conn != null) conn.close();
            es.shutdown();
            System.out.println("Ended...");
        }
    }

    public static void initConnectionPool() throws Exception {
        ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setPassword(password);
        ds.setUsername(userName);
        ds.setUrl(url);
    }

    static class JobExectuor implements Callable<String> {

        private Connection conn;
        private PreparedStatement ps;
        private ResultSet rs;

        public JobExectuor(Connection conn) {
            this.conn = conn;
        }

        public void cancel() throws Exception {
            if(ps != null) {
                System.out.println("Cancelling...");
                try {
                    ps.cancel();
                } catch (Exception e)  {
                    System.out.println(e);
                }
            }
        }
        @Override
        public String call() throws Exception {
            System.out.println("Starting thread execution...");
            try {
                ps = conn.prepareStatement("select site_name from tpt_config_info for update");
                ((SingleThread)Thread.currentThread()).getThreadLocal().set(ps);
                //ps.setQueryTimeout(2);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                    System.out.println("Result: " + rs.getString("site_name"));
            } catch (Exception e) {

                throw e;
            } finally {
                if (ps != null) ps.close();
                if(rs != null) rs.close();
                System.out.println("Ended thread execution...");
            }
            return Thread.currentThread().getName();
        }
    }

    static class MonitorThread extends Thread {
        private Future<String> task;

        public MonitorThread(Future<String> task) {
            this.task=task;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Calling interrupt...");
                boolean cancelled = task.cancel(true);
                //System.out.println("Cancelled with " + cancelled);
            } catch(Exception e) {
            }
        }
    }


}

class ThreadContext extends ThreadLocal<PreparedStatement> {
    private PreparedStatement ps;

    @Override
    public void set(PreparedStatement value) {
        ps = value;
    }

    @Override
    public PreparedStatement get() {
       return ps;
    }
}