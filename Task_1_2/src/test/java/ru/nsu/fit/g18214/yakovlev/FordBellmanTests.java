package ru.nsu.fit.g18214.yakovlev;

import org.junit.Assert;
import org.junit.Test;
public class FordBellmanTests {

    @Test
    public void test5() {
        ListedGraphBuilder graphBuilder = new ListedGraphBuilder(5);
        ListedGraph listedGraph = graphBuilder.construct();
        ShortestPath shortestPath = new FordBellman(listedGraph);
        boolean flag = false;
        try {
            shortestPath.findShortestPath(6, 3);
        } catch (IllegalArgumentException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void test1() {
        ListedGraphBuilder graphBuilder = new ListedGraphBuilder(4);
        graphBuilder.addEdge(0, 1, 2);
        graphBuilder.addEdge(1, 2, 3);
        graphBuilder.addEdge(2, 3, 4);
        ListedGraph listedGraph = graphBuilder.construct();
        ShortestPath shortestPath = new FordBellman(listedGraph);
        Assert.assertEquals(2 + 3 + 4, shortestPath.findShortestPath(0, 3));
        Assert.assertEquals(2 + 3, shortestPath.findShortestPath(0, 2));
        Assert.assertEquals(3, shortestPath.findShortestPath(1, 2));
    }

    @Test
    public void test2() {
        ListedGraphBuilder graphBuilder = new ListedGraphBuilder(4);
        int a = 0;
        int b = 3;
        graphBuilder.addEdge(0, 1, 2);
        graphBuilder.addEdge(1, 2, 3);
        graphBuilder.addEdge(2, 3, 4);
        graphBuilder.addEdge(0, 3, 8);
        ListedGraph listedGraph = graphBuilder.construct();
        ShortestPath shortestPath = new FordBellman(listedGraph);
        Assert.assertEquals(8, shortestPath.findShortestPath(a, b));
    }

    @Test
    public void test3() {
        ListedGraphBuilder graphBuilder = new ListedGraphBuilder(4);
        int a = 0;
        int b = 3;
        graphBuilder.addEdge(0, 1, 2);
        graphBuilder.addEdge(1, 2, 3);
        graphBuilder.addEdge(2, 3, 4);
        graphBuilder.addEdge(0, 2, 4);
        ListedGraph listedGraph = graphBuilder.construct();
        ShortestPath shortestPath = new FordBellman(listedGraph);
        Assert.assertEquals(8, shortestPath.findShortestPath(a, b));
    }
}
