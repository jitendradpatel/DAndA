package threads;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by jpatel on 6/24/15.
 */
public class ThreadControlTest {
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new MyThread());
        t1.start();
        t1.setName("OUTTER");
        int cnt = 0;
        while(true) {
            cnt++;
            Thread.sleep(1000);
            if(cnt == 1) {
                System.out.println("Intruppting...");
                t1.interrupt();
            }
        }
    }
}

class MyThread implements Runnable {
    private int cnt = 0;
    private boolean printNow;
    @Override
    public void run() {
        Thread innert = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if(printNow)
                        System.out.println("Running inside");
                }
            }
        });
        innert.setName("INNER");
        innert.start();
        try {
            while (true) {
                System.out.println("waiting inside primary thread...");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            printNow = true;
            System.out.println("Before");
            try {Thread.sleep(500);} catch (Exception ee) {
                System.out.println("----------");
                ee.printStackTrace();
            }
            System.out.println("After");
            throw new RuntimeException(e);
        }
    }

}