package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by jpatel on 12/3/15.
 */
public class InsertionSort {
    private int[] data;

    InsertionSort(String[] data) {
        this.data = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = Integer.valueOf(data[i]);
        }
    }

    public void sort() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(this);
            for (int j = i; j > 0 && less(data[j], data[j - 1]); j--) {
                exchange(j, j - 1);
            }
        }

    }

    private void exchange(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private static boolean less(int i, int j) {
        return i < j;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stream<String> lines = in.lines().limit(1);
        InsertionSort is = new InsertionSort(lines.findFirst().get().split(" "));
        System.out.println(is);
        is.sort();
        System.out.println(is);
        //8 9 4 3 2 1 0 5 7 6
    }
}
