package ru.nsu.fit.g18214.yakovlev;

import static java.lang.StrictMath.min;

public class ShortestPath {

    /**
     * This function allows you to find a shortest path in the graph, which is submitted by an adjacency matrix.
     * @param d adjacency matrix. I,J value has a path length, if there is a path from i to j, or MAX_INT value, if it's no path
     * @param a from this point you want to find a shortest path
     * @param b to this point you want to find a shortest path
     * @return int shortest path
     */
    public static int findShortestPath(int[][] d, int a, int b) {
        int n = d.length;
        for (int k=0; k<n; ++k)
            for (int i=0; i<n; ++i)
                for (int j=0; j<n; ++j)
                    if (d[i][k] < Integer.MAX_VALUE && d[k][j] < Integer.MAX_VALUE)
                        d[i][j] = min (d[i][j], d[i][k] + d[k][j]);
        return d[a][b];
    }

}
