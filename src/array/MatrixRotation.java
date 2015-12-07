package array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by jpatel on 6/20/15.
 */
public class MatrixRotation {
    public static void main(String[] args) throws Exception {
        int N=5;
        int a[][] = new int[N][N];
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = cnt++;
            }
        }
        printMatrix(N, a, false);
        System.out.println("\n");
        printMatrix(N, a, true);
        String value = "-1";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("Enter x & y (-1 to exit): ");
            value = br.readLine();
            System.out.println(value);
            if("-1".equals(value)) return;
            String[] pixel = value.split("x");
            System.out.println(getPixelAt90D(a, Integer.valueOf(pixel[0]), Integer.valueOf(pixel[1]), true));
        }
    }

    private static int getPixelAt90D(int[][] a, int x, int y, boolean forward) {
        int finalX = x, finalY = y;
        if(forward) {
            finalX = (a.length - y) + 1;
            finalY = x;
        } else {
            finalX = y;
            finalY = (a.length - x) + 1;
        }
        //System.out.println("Resolved at " + finalX + "x" + finalY + ": ");
        return a[finalX-1][finalY-1];
    }

    private static void printMatrix(int n, int[][] a, boolean rotate) {
        StringBuilder sb = new StringBuilder();
        sb.append('\t');
        for (int i = 0; i < 5; i++) {
            sb.append(i + 1).append('\t');
        }
        sb.append('\n').append('-');
        for (int i = 0; i < 5; i++) {
            sb.append("----");
        }
        sb.append('\n');
        for (int i = 0; i < n; i++) {
            sb.append(i+1).append(' ').append('|').append('\t');
            for (int j = 0; j < n; j++) {
                sb.append(rotate ? getPixelAt90D(a, i+1, j+1, true) : a[i][j]).append('\t');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
