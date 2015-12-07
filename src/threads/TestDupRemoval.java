package threads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by jpatel on 9/23/15.
 */
public class TestDupRemoval {
//
//    public static void main(String[] args) throws Exception {
//        int[] a = {11, 12, 13, 13, 13, 24, 24, 30, 33, 45, 45, 57};
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i] + ",");
//        }
//        System.out.println();
//        int[] b = removeDuplicates(a);
//        for (int i = 0; i < b.length; i++) {
//            System.out.print(b[i] + ",");
//        }
//    }
//
//    public static int[] removeDuplicates(int[] values) {
//        if (values == null)
//            return null;
//
//        int[] new_arr = new int[values.length];
//        int temp = 0;
//        int j = 0;
//        //11, 12, 13, 13, 13, 24, 24, 30, 33, 45, 45, 57 ] -
//
//        for (int i = 0; i < values.length; i++) {
//            if (i == 0) {
//                temp = values[0];
//                new_arr[j++] = values[i];
//
//            }
//
//            if (values[i] != temp) {
//                new_arr[j++] = values[i];
//                temp = values[i];
//            }
//
//        }
//
//        int[] ret_arr = new int[j];
//        for (int i = 0; i < j; i++) {
//            ret_arr[i] = new_arr[i];
//        }
//
//        return ret_arr;
//
//    }
//}
//
//class MyIterator<E> implements Iterator<E> {
//
//    private E prevElement;
//    private int counter;
//    private int collectionSize = 0;
//    private ArrayList<E> collection;
//
//    public MyIterator(Collection<E> collection) {
//        this.collection = new ArrayList<>(collection);
//        collectionSize = collection.size();
//    }
//
//    public boolean hasNext() {
//        if (this.collection == null)
//            return false;
//        if (counter < collectionSize)
//            return true;
//        else
//            return false;
//
//    }
//
//    // [ 11, 12, 13, 13, 13, 24, 24, 30, 33, 45, 45, 57 ] -
//    public E next() {
//        if (counter == 0) {
//            prevElement = collection.get(0);
//            counter++;
//            return collection.get(0);
//        }
//        for (int i = counter; i < collectionSize; i++) {
//            if (collection.get(i).equals(prevElement))
//                continue;
//
//            prevElement = collection.get(i);
//            counter = i + 1;
//            return collection.get(i);
//
//
//        }
//    }
}