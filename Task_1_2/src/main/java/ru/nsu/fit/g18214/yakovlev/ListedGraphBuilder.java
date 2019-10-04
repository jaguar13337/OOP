package ru.nsu.fit.g18214.yakovlev;

import java.util.LinkedList;

public class ListedGraphBuilder implements GraphBuilder{

    private LinkedList<Edge> graph;
    private int vertexCnt;

    public ListedGraphBuilder(int vertexCnt) {
        graph = new LinkedList<Edge>();
        this.vertexCnt = vertexCnt;
    }

    /**
     * Add an edge to the graph
     * @param from edge from vertex "from"
     * @param to edge to vertex "to"
     * @param val edge len
     */
    public void addEdge(int from, int to, int val) {
        Edge edge = new Edge(from,to, val);
        graph.add(edge);
        vertexCnt++;
    }


    public ListedGraph construct() {
        return new ListedGraph(vertexCnt, graph);
    }
}
