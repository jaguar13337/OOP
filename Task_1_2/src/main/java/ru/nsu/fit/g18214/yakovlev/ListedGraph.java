package ru.nsu.fit.g18214.yakovlev;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This is a simple representation of graph on a LinkedList.
 */
public class ListedGraph implements Graph, Iterable<Edge> {

    private LinkedList<Edge> graph;
    private int vertexCnt;

    /**
     * Generate a graph with entered length
     * @param cnt a count of vertexes in the graph
     */
    ListedGraph(int cnt, LinkedList<Edge> graph) {
        this.graph = graph;
        this.vertexCnt = cnt;
    }

    /**
     * returns a count of vertexes in graph.
     * @return cnt of vertexes
     */
    public int getVertexCnt() {
        return vertexCnt;
    }

    @Override
    public int getEdgeLen(int a, int b) {
        for (Edge i: graph) {
            if (i.getFrom() == a && i.getTo() == b)
                return i.getLen();
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public Iterator<Edge> iterator() {
        return graph.iterator();
    }
}
