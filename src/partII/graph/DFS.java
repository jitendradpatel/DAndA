package partII.graph;

import scala.Int;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpatel on 11/10/15.
 */
public class DFS {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DFS(AdjuncyListGraph g, int s) {
        marked = new boolean[g.getAdjuncyList().length];
        edgeTo = new int[g.getAdjuncyList().length];
        for (int i = 0; i < edgeTo.length; i++) {

            edgeTo[i] = -1;
        }
        this.s = s;
        dfs(g, s);
    }

    public void dfs(AdjuncyListGraph g, int v) {
        marked[v] = true;
        for (int w : g.getAdjuncy(v)) {
            if (!marked[w]) {
                dfs(g, w);
                edgeTo[w] = v;
            }
        }
    }

    public boolean[] getMarked() {
        return marked;
    }

    public void setMarked(boolean[] marked) {
        this.marked = marked;
    }

    public int[] getEdgeTo() {
        return edgeTo;
    }

    public void setEdgeTo(int[] edgeTo) {
        this.edgeTo = edgeTo;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public static void main(String[] args) throws Exception {
        AdjuncyListGraph g = AdjuncyListGraph.constructGraph();
        DFS dfs = new DFS(g, 0);
        for (int i = 0; i < g.getAdjuncyList().length; i++) {
            System.out.println(i + " " + (dfs.getMarked()[i] ? "T" : "F") + " " + dfs.getEdgeTo()[i]);
        }
        System.out.println(dfs.hasPathTo(11));
        System.out.println(dfs.pathTo(6));
    }

    public boolean hasPathTo(int v) {
        int cv = edgeTo[v];
        while(cv != s && cv != -1) {
            cv = edgeTo[cv];
        }
        return cv == s;
    }

    public String pathTo(int v) {
        if(!hasPathTo(v)) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(v);
        int cv = edgeTo[v];
        while(cv != s && cv != -1) {
            sb.append(" -> ").append(cv);
            cv = edgeTo[cv];
        }
        sb.append(" -> ").append(s);
        return sb.toString();
    }
}
