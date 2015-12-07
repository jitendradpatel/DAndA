package threads;

/**
 * Created by jpatel on 9/29/15.
 */
public class ThreadLocalTest {

    public static void main(String[] args) throws Exception {
        MyThreadTest mtt = new MyThreadTest();
        mtt.start();
        Thread.sleep(2000);
        mtt.interrupt();
    }

}

class MyThreadTest extends Thread {
    @Override
    public void run() {
        MyThreadLocal.set(Thread.currentThread().getName());
        while (true) {
            try {
                System.out.println("Inside Thread: " + MyThreadLocal.get());
                Thread.sleep(2000);
                System.out.println("Interrupted: " + Thread.currentThread().interrupted());
            } catch (Exception e) {
            }
        }
    }
}

class MyThreadLocal {
    public static final ThreadLocal<String> tl = new ThreadLocal<>();

    public static void set(String threadName) {
        tl.set(threadName);
    }

    public static void unset() {
        tl.remove();
    }

    public static String get() {
        return tl.get();
    }
}
