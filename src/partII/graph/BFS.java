package partII.graph;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jpatel on 11/24/15.
 */
public class BFS {

    private boolean marked[];
    private int edgeTo[];
    private int sourceNode;
    private int d;
    private int c;
    public BFS(AdjuncyListGraph g, int s) {
        this.sourceNode = s;
        marked = new boolean[g.getAdjuncyList().length];
        edgeTo = new int[g.getAdjuncyList().length];
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = -1;
        }
        bfs(g, s);
    }

    public void bfs(AdjuncyListGraph g, int v) {
        Queue<Integer> q = new LinkedBlockingQueue<>();
        q.add(v);
        edgeTo[v] = v;
        marked[v] = true;
        while (!q.isEmpty()) {
            int cv = q.poll();
            for(int adv : g.getAdjuncy(cv)) {
                if(marked[adv]) continue;
                q.add(adv);
                marked[adv] = true;
                edgeTo[adv] = cv;
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
        return sourceNode;
    }

    public void setS(int s) {
        this.sourceNode = s;
    }

    public boolean hasPathTo(int v) {
        int cv = edgeTo[v];
        while(cv != sourceNode && cv != -1) {
            cv = edgeTo[cv];
        }
        return cv == sourceNode;
    }

    public String pathTo(int v) {
        if(!hasPathTo(v)) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(v);
        int cv = edgeTo[v];
        while(cv != sourceNode && cv != -1) {
            sb.append(" -> ").append(cv);
            cv = edgeTo[cv];
        }
        sb.append(" -> ").append(sourceNode);
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        AdjuncyListGraph g = AdjuncyListGraph.constructGraph();
        BFS bfs = new BFS(g, 0);
        for (int i = 0; i < g.getAdjuncyList().length; i++) {
            System.out.println(i + " " + (bfs.getMarked()[i] ? "T" : "F") + " " + bfs.getEdgeTo()[i]);
        }
        System.out.println(bfs.hasPathTo(3));
        System.out.println(bfs.pathTo(3));

    }
}
