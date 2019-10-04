package ru.nsu.fit.g18214.yakovlev;

class Edge {
    private int from;
    private int to;
    private int len;

    Edge (int from,int to, int len) {
        this.from = from;
        this.to = to;
        this.len = len;
    }

    int getFrom() {
        return from;
    }

    int getTo() {
        return to;
    }

    int getLen() {
        return len;
    }
}
