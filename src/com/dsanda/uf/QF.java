package com.dsanda.uf;

import java.util.Scanner;

/**
 * Created by jpatel on 5/16/15.
 */
public class QF {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        log("Enter N: ");
        String str = s.nextLine();
        QU uf = new QU(Integer.valueOf(str));
        log("Enter pair: ");
        while(!(str = s.nextLine()).isEmpty()) {
            String[] pairs = str.split(" ");
            uf.union(Integer.valueOf(pairs[0]), Integer.valueOf(pairs[1]));
        }
        uf.print();
    }

    private int n;
    private int a[];

    public QF(int n) {
        this.n = n;
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        log("Initilized array a w/ n objects.");
    }

    public void union(int p, int q) {
        if(!connected(p, q)) {
            log(p + " is not connected to " +  q + ". Connecting...");
            for (int i = 0; i < n; i++) {
                if(a[i] == a[p]) a[i] = a[q];
            }
        } else
            log("Already connected. Skipping...");
    }

    public boolean connected(int p, int q) {
        return a[p] == a[q];
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void log(String a) {
        System.out.println("[QU] " + a);
    }

}
