package ru.nsu.fit.g18214.yakovlev;

public interface Graph {
    /**
     * Returns a count of vertexes
     * @return int cnt of vertexes in graph
     */
    int getVertexCnt();

    /**
     * This function tries to get len of edge, if it's exist.
     * @return len of edge, if it's exists or MAX_INTEGER otherwise
     */
    int getEdgeLen(int a, int b);
}
