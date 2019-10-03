package ru.nsu.fit.g18214.yakovlev;

import org.junit.Assert;
import org.junit.Test;
public class ShortestPathTests {

    @Test
    public void test4() {
        ShortestPathFunc shortestPathFunc = new ShortestPath(null);
        boolean flag = false;
        try {
            shortestPathFunc.findShortestPath(4, 10);
        } catch (NullableGraphException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    void test5() {
        GraphList graphList = new GraphList(5);
        ShortestPathFunc shortestPathFunc = new ShortestPath(graphList);
        boolean flag = false;
        try {
            shortestPathFunc.findShortestPath(5, 3);
        } catch (ShortestPathWrongArgumentException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Test
    void test1() {
        GraphList graphList = new GraphList(4);
        ShortestPathFunc shortestPathFunc = new ShortestPath(graphList);
        graphList.addEdge(0, 1, 2);
        graphList.addEdge(1, 2, 3);
        graphList.addEdge(2, 3, 4);
        Assert.assertEquals(2 + 3 + 4, shortestPathFunc.findShortestPath(0, 3));
        Assert.assertEquals(2 + 3, shortestPathFunc.findShortestPath(0, 2));
        Assert.assertEquals(3, shortestPathFunc.findShortestPath(1, 2));
        graphList.addEdge(0, 3, 5);
        Assert.assertEquals(5, shortestPathFunc.findShortestPath(0, 3));

    }

    @Test
    public void test2() {
        GraphList graphList = new GraphList(4);
        ShortestPathFunc shortestPathFunc = new ShortestPath(graphList);
        int a = 0;
        int b = 3;
        graphList.addEdge(0, 1, 2);
        graphList.addEdge(1, 2, 3);
        graphList.addEdge(2, 3, 4);
        graphList.addEdge(0, 3, 8);
        Assert.assertEquals(8, shortestPathFunc.findShortestPath(a, b));
    }

    @Test
    public void test3() {
        GraphList graphList = new GraphList(4);
        ShortestPathFunc shortestPathFunc = new ShortestPath(graphList);
        int a = 0;
        int b = 3;
        graphList.addEdge(0, 1, 2);
        graphList.addEdge(1, 2, 3);
        graphList.addEdge(2, 3, 4);
        graphList.addEdge(0, 2, 4);
        Assert.assertEquals(8, shortestPathFunc.findShortestPath(a, b));
    }
}
