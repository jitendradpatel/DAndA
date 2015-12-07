package com.dsanda.bst;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by jpatel on 7/13/15.
 */
public class BST {
    public static void main(String[] args) throws Exception {
//        Scanner s = new Scanner(System.in);
//        System.out.println("Enter Node Value: ");
//        String str = null;
//        BST bst = new BST();
//        while(!(str = s.nextLine()).isEmpty()) {
//            bst.put(Integer.valueOf(str));
//        }
        BST bst = new BST();
        int size = 10;
//        Random right = new Random();
//        for (int i = 0; i < size; i++) {
//            int rv = right.nextInt(100);
//            bst.put(rv);
//            System.out.print(rv + " ");
//        }
        int[] a = {10,5,11,3,8,9,7,6};
        for (int i = 0; i < a.length; i++) {
            bst.put(a[i]);
        }
        System.out.println();
        bst.printInOrder();
        int delete = 5;
        System.out.println("`: " + delete);
        bst.delete(delete);
        System.out.println();
        bst.printInOrder();
    }

    private Node root;
    private boolean useR = true;

    public void put(int v) {
        if(useR)
            root = putR(root, v);
        else
            root = putR(root, v);
    }

    private Node putR(Node x, int v) {
        if(x == null) return new Node(v);
        if(v < x.v)
            x.l = putR(x.l, v);
        else if(v > x.v)
            x.r = putR(x.r, v);
        else
            x.v = v;
        return x;
    }

    public void delete(int v) {
        delete(null, root, v);
    }

    private void delete(Node p, Node x, int v) {
        if(x == null) return;
        if(v < x.v)
            delete(x, x.l, v);
        else if(v > x.v)
            delete(x, x.r, v);
        else {
            Node nodeToBeReplacedWith = deleteMain(x);
            if(x.v < p.v)
                p.l  = nodeToBeReplacedWith;
            else
                p.r = nodeToBeReplacedWith;
        }
    }

    private Node deleteMain(Node x) {
        if(x.r == null && x.l == null) return null;
        else if(x.r == null) return x.l;
        Node findNode = x.r;
        while(findNode.l != null) {
            findNode = findNode.l;
        }
        return findNode;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node x) {
        if(x == null) return;
        if(x.l != null) printInOrder(x.l);
        System.out.print(x.v + " ");
        if(x.r != null) printInOrder(x.r);
    }
}

class Node {
    public int v;
    public Node l, r;

    public Node(int v) {
        this.v=v;
    }

    @Override
    public String toString() {
        return l + " " + v + " " + r;
    }
}