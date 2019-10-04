package ru.nsu.fit.g18214.yakovlev;

public interface GraphBuilder {
    /**
     * Add an edge to the graph from vertex 'from' to vertex 'to' with len 'len'
     * @param from start vertex of edge
     * @param to end vertex of edge
     * @param len edge len
     */
    void addEdge(int from, int to, int len);

    /**
     * Generates new Graph with already pushed edges.
     * @return returns new Graph element.
     */
    Graph construct();
}
