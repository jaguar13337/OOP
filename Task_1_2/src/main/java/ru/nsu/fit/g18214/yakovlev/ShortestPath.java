package ru.nsu.fit.g18214.yakovlev;

public class ShortestPath implements ShortestPathFunc {


    private GraphList graphList;

    public ShortestPath(GraphList graphList) {this.graphList = graphList;}

    /**
     * This function allows you to find a shortest path in the graphList.
     * @param a from this point you want to find a shortest path.
     * @param b to this point you want to find a shortest path.
     * @return int shortest path
     */
    public int findShortestPath (int a, int b) throws NullableGraphException {
        if (graphList == null)
            throw new NullableGraphException();
        else if (a >= graphList.getVertexCnt() || b >= graphList.getVertexCnt())
            throw new ShortestPathWrongArgumentException("Argument larger than vertexes count\n");
        else {
            int[] ary = graphList.getShortestPaths(a);
            if (ary == null) {
                int vertexCnt = graphList.getVertexCnt();
                ary = new int[vertexCnt];
                for (int i = 0; i < vertexCnt; i++)
                    ary[i] = Integer.MAX_VALUE;
                ary[a] = 0;
                while (true) {
                    boolean any = false;
                    for (Edge j : graphList)
                        if (ary[j.getFrom()] < Integer.MAX_VALUE)
                            if (ary[j.getTo()] > ary[j.getFrom()] + j.getLen()) {
                                ary[j.getTo()] = ary[j.getFrom()] + j.getLen();
                                any = true;
                            }
                    if (!any) break;
                }
                graphList.addShortestPathsFromVertex(a, ary);
            }
            return ary[b];
        }
    }
}
