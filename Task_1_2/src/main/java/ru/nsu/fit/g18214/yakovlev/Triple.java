package ru.nsu.fit.g18214.yakovlev;

class Triple {
    private int from;
    private int to;
    private int len;

    Triple(int from,int to, int len) {
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
