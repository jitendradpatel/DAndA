package clazz;

/**
 * Created by jpatel on 10/19/15.
 */
public class ClazzTest {

    public static void main(String[] args) throws Exception {
        Class<Object> c1 = Object.class;
        Class<A> ai = A.class;
        Class<B> bc = B.class;
        Class<C> cc = C.class;

        System.out.println(ai.isAssignableFrom(c1));
        System.out.println(c1.isAssignableFrom(ai));
        System.out.println(bc.isAssignableFrom(cc));


    }


}

interface A {

}

class B implements A {

}

class C extends B {

}