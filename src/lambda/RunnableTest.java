package lambda;

/**
 * Created by jpatel on 10/5/15.
 */
public class RunnableTest {
    public static void main(String[] args) throws Exception {
        Runnable r = () -> System.out.println("Hi!");
        r.run();
    }
}
