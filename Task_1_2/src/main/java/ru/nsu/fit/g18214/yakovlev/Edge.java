package ru.nsu.fit.g18214.yakovlev;

public class Edge {
    private int from;
    private int to;
    private int len;

    public Edge(int from,int to, int len) {
        this.from = from;
        this.to = to;
        this.len = len;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getLen() {
        return len;
    }
}
