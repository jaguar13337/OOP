package ru.nsu.fit.g18214.yakovlev;

import org.junit.*;

public class ShortestPathTests {

    @Test
    public void test1() {
        int[][] matr = new int[4][4];
        int a = 0;
        int b = 3;
        for(int i = 0; i<4; i++)
            for (int j = 0; j<4; j++)
                matr[i][j] = Integer.MAX_VALUE;
        matr[0][1] = 2;
        matr[1][2] = 3;
        matr[2][3] = 4;
        Assert.assertEquals(2+3+4, ShortestPath.findShortestPath(matr,a,b));
    }

    @Test
    public void test2() {
        int[][] matr = new int[4][4];
        int a = 0;
        int b = 3;
        for(int i = 0; i<4; i++)
            for (int j = 0; j<4; j++)
                matr[i][j] = Integer.MAX_VALUE;
        matr[0][1] = 2;
        matr[1][2] = 3;
        matr[2][3] = 4;
        matr[0][3] = 8;
        Assert.assertEquals(8, ShortestPath.findShortestPath(matr,a,b));
    }

    @Test
    public void test3() {
        int[][] matr = new int[4][4];
        int a = 0;
        int b = 3;
        for(int i = 0; i<4; i++)
            for (int j = 0; j<4; j++)
                matr[i][j] = Integer.MAX_VALUE;
        matr[0][1] = 2;
        matr[0][2] = 4;
        matr[1][2] = 3;
        matr[2][3] = 4;
        Assert.assertEquals(8, ShortestPath.findShortestPath(matr,a,b));
    }
}
