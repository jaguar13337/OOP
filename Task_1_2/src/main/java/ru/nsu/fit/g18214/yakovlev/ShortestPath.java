package ru.nsu.fit.g18214.yakovlev;

public interface ShortestPath {
    /**
     * Finds shortest path in graph from vertex a to vertex b
     * @param a the vertex 'from'
     * @param b the vertex 'to'
     * @return int shortest path from a to b
     */
    int findShortestPath(int a, int b);
}
