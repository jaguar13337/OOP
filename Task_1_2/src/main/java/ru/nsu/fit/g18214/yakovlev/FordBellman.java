package ru.nsu.fit.g18214.yakovlev;

import org.apache.commons.math3.exception.NullArgumentException;

public class FordBellman implements ShortestPath {

    private ListedGraph listedGraph;
    private int[][] shortestPaths = null;

    public FordBellman(ListedGraph listedGraph) throws NullArgumentException {
        if (listedGraph == null)
            throw new NullArgumentException();
        this.listedGraph = listedGraph;
        this.shortestPaths = new int[listedGraph.getVertexCnt()][];
        for (int i = 0; i<listedGraph.getVertexCnt(); i++)
            shortestPaths[i] = null;
    }

    /**
     * This function allows you to find a shortest path in the listedGraph.
     * @param a from this point you want to find a shortest path.
     * @param b to this point you want to find a shortest path.
     * @return int shortest path
     */
    public int findShortestPath(int a, int b) throws IllegalArgumentException {
        if (a >= listedGraph.getVertexCnt() || b >= listedGraph.getVertexCnt())
            throw new IllegalArgumentException("Argument larger than vertexes count\n");
        else {
            int[] ary = shortestPaths[a];
            if (ary == null) {
                int vertexCnt = listedGraph.getVertexCnt();
                ary = new int[vertexCnt];
                for (int i = 0; i < vertexCnt; i++)
                    ary[i] = Integer.MAX_VALUE;
                ary[a] = 0;
                while (true) {
                    boolean any = false;
                    for (Edge j : listedGraph)
                        if (ary[j.getFrom()] < Integer.MAX_VALUE)
                            if (ary[j.getTo()] > ary[j.getFrom()] + j.getLen()) {
                                ary[j.getTo()] = ary[j.getFrom()] + j.getLen();
                                any = true;
                            }
                    if (!any) break;
                }
                shortestPaths[a] = ary;
            }
            return ary[b];
        }
    }
}
