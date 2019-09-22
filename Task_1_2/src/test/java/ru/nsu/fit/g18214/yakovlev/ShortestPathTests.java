package ru.nsu.fit.g18214.yakovlev;

import org.junit.*;

public class ShortestPathTests {

    @Test
    public void test1() {
        Graph graph = new Graph(4);
        int a = 0;
        int b = 3;
        graph.addEdge(0,1,2);
        graph.addEdge(1,2,3);
        graph.addEdge(2,3,4);
        Assert.assertEquals(2+3+4, graph.findShortestPath(a,b));
    }

    @Test
    public void test2() {
        Graph graph = new Graph(4);
        int a = 0;
        int b = 3;
        graph.addEdge(0,1,2);
        graph.addEdge(1,2,3);
        graph.addEdge(2,3,4);
        graph.addEdge(0,3,8);
        Assert.assertEquals(8,graph.findShortestPath(a,b));
    }

    @Test
    public void test3() {
        Graph graph = new Graph(4);
        int a = 0;
        int b = 3;
        graph.addEdge(0,1,2);
        graph.addEdge(1,2,3);
        graph.addEdge(2,3,4);
        graph.addEdge(0,2,4);
        Assert.assertEquals(8, graph.findShortestPath(a,b));
    }
}
