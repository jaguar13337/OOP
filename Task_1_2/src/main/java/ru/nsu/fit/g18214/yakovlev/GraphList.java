package ru.nsu.fit.g18214.yakovlev;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This is a simple representation of graph on a LinkedList.
 */
public class GraphList implements Graph, Iterable<Edge> {

    private LinkedList<Edge> graph;
    private int[][] shortestPaths;
    private int vertexCnt;

    private void nullShortestPaths() {
        for(int i = 0; i<vertexCnt; i++)
            shortestPaths[i] = null;
    }

    /**
     * Generate a graph with entered length
     * @param cnt a count of vertexes in the graph
     */
    public GraphList(int cnt) {
        this.graph = new LinkedList<Edge>();
        this.vertexCnt = cnt;
        this.shortestPaths = new int[cnt][];
        nullShortestPaths();
    }

    /**
     * Returns an array of shortest paths from vertex a
     * @param a vertex, from which we want to get an array of shortest paths
     * @return an int array of shortest paths
     */
    @Override
    public int[] getShortestPaths(int a) {
        return shortestPaths[a];
    }

    /**
     * Add an array of shortest paths from vertex a to all others vertexes
     * @param a a vertex from which we have shortest paths
     * @param paths an int array of shortest paths
     */
    @Override
    public void addShortestPathsFromVertex(int a, int[] paths) {
        shortestPaths[a] = paths;
    }

    /**
     * Add an edge to the graph
     * @param from edge from vertex "from"
     * @param to edge to vertex "to"
     * @param val edge len
     */
    public void addEdge (int from, int to, int val) {
        Edge edge = new Edge(from,to, val);
        graph.add(edge);
        vertexCnt++;
        this.shortestPaths = new int[vertexCnt][];
        nullShortestPaths();
    }

    /**
     * returns a count of vertexes in graph.
     * @return cnt of vertexes
     */
    public int getVertexCnt() {
        return vertexCnt;
    }


    @Override
    public Iterator<Edge> iterator() {
        return graph.iterator();
    }
}
