package array;

import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by jpatel on 6/20/15.
 */
public class MatrixRotationLinkedList {
    public static void main(String[] args) throws Exception {
        int N=5;
        int a[][] = new int[N][N];
        int noOfLayers = N/2;
        LinkedList[] layer = new LinkedList[noOfLayers];
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = cnt++;
            }
        }
        printMatrix(N, a, false);
        System.out.println("\n");
        printMatrix(N, a, true);

        //Initialize linkedlist
        for (int l = 0; l < noOfLayers; l++) {
            layer[l] = new LinkedList();
            //Iterate top
            for (int i = l; i < N - l; i++) {
                layer[l].add(a[l][i]);
            }
            //Iterate right
            for (int i = l+1; i < N - l; i++) {
                layer[l].add(a[i][N - l - 1]);
            }
            //Iterate bottom
            for (int i = N - l - 2; i >= l; i--) {
                layer[l].add(a[N - l - 1][i]);
            }
            //Iterate left
            for (int i = N - l - 2; i > l; i--) {
                layer[l].add(a[i][l]);
            }
        }
        printMatrix(layer);

        //check rotation
        rotateAt90D(layer, N);

        //Print again
        printMatrix(layer);
    }

    private static void rotateAt90D(LinkedList[] layer, int n) {
        for (int l = 0; l < layer.length; l++) {
            System.out.println("Iterating for " + (n-l-1));
            for (int i = l; i < n - l - 1; i++) {
                layer[l].addFirst(layer[l].pollLast());
            }
        }
    }

    private static void printMatrix(LinkedList[] layer) {
        for (int l = 0; l < layer.length; l++) {
            StringBuilder sb = new StringBuilder("Layer[").append(l + 1).append("] ");
            Iterator<Integer> iter = layer[l].listIterator();
            iter.forEachRemaining(v -> {
                        sb.append(v).append('\t');
                    }
            );
            System.out.println(sb.toString());
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
        for (int i = 0; i < n; i++) {
            sb.append(i + 1).append('\t');
        }
        sb.append('\n').append('-');
        for (int i = 0; i < n; i++) {
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
