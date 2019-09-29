package ru.nsu.fit.g18214.yakovlev;

public class ShortestPath {
    /**
     * This function allows you to find a shortest path in the graph.
     * @param graph this is the graph, constructed by Graph class.
     * @param a from this point you want to find a shortest path.
     * @param b to this point you want to find a shortest path.
     * @return int shortest path
     */
    public static int findShortestPath(Graph graph, int a, int b) {
        int vertexCnt = graph.getVertexCnt();
        int[] ary = new int[vertexCnt];
        for (int i = 0; i<vertexCnt; i++)
            ary[i] = Integer.MAX_VALUE;
        ary[a] = 0;
        while(true) {
            boolean any = false;
            for (Edge j: graph)
                if (ary[j.getFrom()] < Integer.MAX_VALUE)
                    if (ary[j.getTo()] > ary[j.getFrom()] + j.getLen()) {
                        ary[j.getTo()] = ary[j.getFrom()] + j.getLen();
                        any = true;
                    }
            if (!any)  break;
        }
        return ary[b];
    }
}
