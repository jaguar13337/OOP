package ru.nsu.fit.g18214.yakovlev;

public interface Graph {
    /**
     * Add an edge to the graph from vertex 'from' to vertex 'to' with len 'len'
     * @param from start vertex of edge
     * @param to end vertex of edge
     * @param len edge len
     */
    void addEdge(int from, int to, int len);

    /**
     * Returns a count of vertexes
     * @return int cnt of vertexes in graph
     */
    int getVertexCnt();

    /**
     * Added an array of shortest paths from vertex a to an A.
     * Array of shortest paths must be cleared, if you add a new edge after it's counting
     * @param a vertex from which we already count all shortest paths to all other vertexes
     * @param paths an array of shortest paths
     */
    void addShortestPathsFromVertex(int a, int[] paths);

    /**
     * Returns an array of shortest paths from vertex a
     * @param a vertex, from which we want to get an array of shortest paths
     * @return an int array of shortest paths
     */
    int[] getShortestPaths(int a);
}
