package ru.nsu.fit.g18214.yakovlev;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This is a simple representation of graph.
 */
public class Graph implements Iterable<Edge> {
    private LinkedList<Edge> graph;
    private int vertexCnt;
    /**
     * Generate a graph with entered length
     * @param cnt a count of vertexes in the graph
     */
    public Graph(int cnt) {
        this.graph = new LinkedList<Edge>();
        this.vertexCnt = cnt;
    }

    /**
     * Add an edge to the graph
     * @param from edge from vertex "from"
     * @param to edge to vertex "to"
     * @param val edge len
     */
    public void addEdge(int from, Integer to, Integer val) {
        Edge edge = new Edge(from,to, val);
        graph.add(edge);
    }


    /**
     * returns a count of vertexes in graph.
     * @return cnt of vertexes
     */
    public int getVertexCnt() {
        return vertexCnt;
    }


    /**
     * returns a len of edge from A to B. If there's no edge -> returns -1
     * @param from vertex from
     * @param to vertex to
     * @return int = len of edge.
     */
    public int getEdgeLen(int from, int to) {
        for (Edge i: graph) {
            if (i.getFrom() == from && i.getTo() == to)
                return i.getLen();
        }
        return -1;
    }

    @Override
    public Iterator<Edge> iterator() {
        return graph.iterator();
    }
}
