package com.dsanda.uf;

import java.util.Scanner;

/**
 * Created by jpatel on 5/16/15.
 */
public class QU {
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

    public QU(int n) {
        this.n = n;
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        log("Initilized array with " + n + " objects.");
    }

    public void union(int p, int q) {
        int rofp = root(p);
        int rofq = root(q);
        if(rofp != rofq) {
            log(p + " is not connected to " +  q + ". Connecting...");
            a[rofp] = rofq;
        } else
            log("Already connected. Skipping...");
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int o) {
        int rofo = a[o];
        while(a[rofo] != rofo) rofo = a[rofo];
        return rofo;
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
