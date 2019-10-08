package ru.nsu.fit.g18214.yakovlev;

import org.apache.commons.math3.exception.NullArgumentException;

/**
 * A class that finds the shortest path in graph from vertex a to vertex b.
 */
public class FordBellman implements ShortestPath {

    private Graph listedGraph;
    private int[][] shortestPaths = null;


    public FordBellman(Graph listedGraph) throws NullArgumentException {
        if (listedGraph == null)
            throw new NullArgumentException();
        this.listedGraph = listedGraph;
        this.shortestPaths = new int[listedGraph.getVertexCnt()][];
        for (int i = 0; i < listedGraph.getVertexCnt(); i++)
            shortestPaths[i] = null;
    }

    /**
     * This function allows you to find a shortest path in the listedGraph.
     *
     * @param a from this point you want to find a shortest path.
     * @param b to this point you want to find a shortest path.
     * @return int shortest path
     */
    public int findShortestPath(int a, int b) throws IllegalArgumentException {
        if (a >= listedGraph.getVertexCnt() || b >= listedGraph.getVertexCnt())
            throw new IllegalArgumentException("Argument larger than vertexes count\n");
        else {
            int[] pathsFromA = shortestPaths[a];
            if (pathsFromA == null) {
                int vertexCnt = listedGraph.getVertexCnt();
                pathsFromA = new int[vertexCnt];
                for (int i = 0; i < vertexCnt; i++)
                    pathsFromA[i] = Integer.MAX_VALUE;
                pathsFromA[a] = 0;
                boolean any = true;
                while (any) {
                    any = false;
                    for (int from = 0; from < vertexCnt; from++) {
                        for (int to = 0; to < vertexCnt; to++) {
                            int len = listedGraph.getEdgeLen(from, to);
                            if (len != Integer.MAX_VALUE) {
                                if (pathsFromA[to] > pathsFromA[from] + len) {
                                    pathsFromA[to] = pathsFromA[from] + len;
                                    any = true;
                                }
                            }
                        }
                    }
                    shortestPaths[a] = pathsFromA;
                }
            }
            return pathsFromA[b];
        }
    }
}
