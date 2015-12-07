package partII.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by jpatel on 10/28/15.
 */
public class AdjuncyListGraph {

    private int n;
    private List<Integer>[] adjList;

    public AdjuncyListGraph(int n) {
        this.n = n;
        adjList = new List[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w);
        if(v != w)
            adjList[w].add(v);
    }

    public List<Integer>[] getAdjuncyList() {
        return adjList;
    }

    public List<Integer> getAdjuncy(int v) {
        return adjList[v];
    }

    public int degree(int v) {
        return adjList[v].size();
    }

    public int maxDegree() {
        int max = 0;
        for (int i = 0; i < adjList.length; i++) {
            if(max < adjList[i].size())
                max = adjList[i].size();
        }
        return max;
    }

    public boolean hasLoop() {
        boolean hasLoop = false;
        for (int i = 0; i < adjList.length; i++) {
            if(adjList[i].contains(i)) {
                hasLoop = true;
                break;
            }
        }
        return hasLoop;
    }

    public boolean hasParallelEdges() {
        boolean hasParallelEdges = false;
        for (int i = 0; i < adjList.length; i++) {
            int all = adjList[i].size();
            Set<Integer> s = new TreeSet<>(adjList[i]);
            int unique = s.size();
            if(all != unique) {
                hasParallelEdges = true;
                break;
            }
        }
        return hasParallelEdges;
    }

    public static void main(String[] args) throws Exception {
        AdjuncyListGraph g = constructGraph();
        System.out.println();
        List<Integer>[] al = g.getAdjuncyList();
        for (int i = 0; i < al.length; i++) {
            StringBuilder sb = new StringBuilder(String.valueOf(i));
            sb.append(" -> [");
            for(Integer w : al[i]) {
                sb.append(w).append(',');
            }
            sb.append(']');
            System.out.println(sb);
        }
        System.out.println();
        System.out.println("Degree: " + g.degree(0));
        System.out.println("Max Degree: " + g.maxDegree());
        System.out.println("Has Loop: " + g.hasLoop());
        System.out.println("Has Parallel Edges: " + g.hasParallelEdges());
    }

    public static AdjuncyListGraph constructGraph() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("/Users/jpatel/IdeaProjects/DSAndA/in/graph.txt"));
        lines = lines.filter(l -> !l.trim().isEmpty());
        int noOfVertices = 13;
        System.out.println("Constructing graph with [" + noOfVertices + "] vertices...");
        AdjuncyListGraph g = new AdjuncyListGraph(noOfVertices);
        lines.forEach(l -> {
            String[] vertices = l.split("-");
            g.addEdge(Integer.valueOf(vertices[0]), Integer.valueOf(vertices[1]));
        });
        return g;
    }
}
