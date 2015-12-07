package hackerrank;

import junit.framework.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Stream;

public class Preorder {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stream<String> lines = in.lines().limit(1);
        BinaryTree bt = new BinaryTree();
        int[] index = {0};
        String[] vals = lines.findFirst().get().split(" ");
        for (int i = 0; i < vals.length; i++) {
            bt.insertLoop(Integer.valueOf(vals[i]));
        }
        bt.preorder();
        bt.inorder();
        bt.postorder();
        System.out.println("Search:" +  bt.search(3).data);
        Node sn = bt.search(3);
        //Assert.assertNotNull(sn);
        //Assert.assertEquals(3, sn.data);
        sn = bt.search(9);
        //Assert.assertNull(sn);
        //System.out.println(bt.height(bt.getRoot()));
        bt.topView(bt.getRoot());
        System.out.println();
        bt.levelOrder(bt.getRoot());
        try {
            in.close();
        } catch (Exception e) {
        }
    }
    
}

class BinaryTree {
    private Node root;

    public BinaryTree() {
    }

    public Node getRoot() {
        return root;
    }

    public void insert(int v) {
        root = insert(root, v);
    }

    private Node insert(Node p, int v) {
        if(p == null)
            return new Node(v);
        if(v < p.data)
            p.left = insert(p.left, v);
        else if(v > p.data)
            p.right = insert(p.right, v);
        return p;
    }

    public void insertLoop(int v) {
        Node n = new Node(v);
        if(root == null) {
            root = n;
            return;
        }
        Node cn = root;
        while(true) {
            if(v < cn.data) {
                if(cn.left == null) {
                    cn.left = n;
                    break;
                }
                cn = cn.left;
            }
            else if(v >= cn.data) {
                if(cn.right == null) {
                    cn.right = n;
                    break;
                }
                cn = cn.right;
            }
            else
                return;
        }
    }

    public void preorder() {
        StringBuilder sb = new StringBuilder("Preorder: ");
        preorder(root, sb);
        System.out.println(sb);
    }

    public void inorder() {
        StringBuilder sb = new StringBuilder("Inorder: ");
        inorder(root, sb);
        System.out.println(sb);
    }

    public void postorder() {
        StringBuilder sb = new StringBuilder("Postorder: ");
        postorder(root, sb);
        System.out.println(sb);
    }


    private void preorder(Node n, StringBuilder sb) {
        if(n == null) return;
        sb.append(n.data).append(" ");
        preorder(n.left, sb);
        preorder(n.right, sb);
    }

    private void inorder(Node n, StringBuilder sb) {
        if(n == null) return;
        inorder(n.left, sb);
        sb.append(n.data).append(" ");
        inorder(n.right, sb);
    }

    private void postorder(Node n, StringBuilder sb) {
        if(n == null) return;
        postorder(n.left, sb);
        postorder(n.right, sb);
        sb.append(n.data).append(" ");
    }

    public Node search(int v) {
        Node n = null;
        if(root == null) return null;
        Node cn = root;
        while(cn != null) {
            if (v < cn.data)
                cn = cn.left;
            else if (v > cn.data)
                cn = cn.right;
            else {
                n = cn;
                break;
            }
        }
       return n;
    }

    public int height(Node root) {
        if(root == null) return 0;
        int l = height(root.left);
        int r = height(root.right);
        if(l < r)
            return r+1;
        else
            return l+1;
    }

    public void topView(Node root) {
        if(root == null) return;
        Node cn = root;
        Stack<Integer> s = new Stack<>();
        while(cn != null) {
            s.push(cn.data);
            cn = cn.left;
        }
        while(!s.isEmpty())
            System.out.print(s.pop() + " ");
        cn = root.right;
        Queue<Integer> q = new LinkedList<>();
        while(cn != null) {
            q.add(cn.data);
            cn = cn.right;
        }
        while(!s.isEmpty())
            System.out.print(q.poll() + " ");
    }

    public void levelOrder(Node root) {
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();
        LinkedList<Integer> lo = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            Node n = q.poll();
            lo.add(n.data);
            if(n.left != null) {
                q.add(n.left);
            }
            if(n.right != null) {
                q.add(n.right);
            }
        }
        while (!lo.isEmpty())
            System.out.print(lo.poll() + " ");
    }
}

class Node {
    public int data;
    public Node left, right;

    public Node(int val) {
        this.data = val;
    }
}