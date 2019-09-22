package ru.nsu.fit.g18214.yakovlev;

import java.util.LinkedList;

public class Graph {
    private LinkedList<Triple> graph;
    private int vertexCnt;
    /**
     * Generate a graph with entered length
     * @param cnt a count of vertexes in the graph
     */
    public Graph(int cnt) {
        this.graph = new LinkedList<Triple>();
        this.vertexCnt = cnt;
    }

    /**
     * Add an edge to the graph
     * @param from edge from vertex "from"
     * @param to edge to vertex "to"
     * @param val edge len
     */
    public void addEdge(int from, Integer to, Integer val) {
        Triple triple = new Triple(from,to, val);
        graph.add(triple);
    }

    /**
     * This function allows you to find a shortest path in the graph.
     * @param a from this point you want to find a shortest path
     * @param b to this point you want to find a shortest path
     * @return int shortest path
     */
    public int findShortestPath(int a, int b) {
        int[] ary = new int[vertexCnt];
        for (int i = 0; i<vertexCnt; i++)
            ary[i] = Integer.MAX_VALUE;
        ary[a] = 0;
        while(true) {
            boolean any = false;
            for (Triple j: graph)
                if (ary[j.getFrom()] < Integer.MAX_VALUE)
                    if (ary[j.getTo()] > ary[j.getFrom()] + j.getLen()) {
                        ary[j.getTo()] = ary[j.getFrom()] + j.getLen();
                        any = true;
                    }
            if (!any)  break;
        }
        return ary[b];
    }
}
