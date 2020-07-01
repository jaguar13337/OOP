package ru.nsu.fit.g18214.yakovlev.dsl.engine.Model

class Checkpoint implements Comparable<Checkpoint> {
    Date date
    Integer exc
    Integer good
    Integer sat

    @Override
    int compareTo(Checkpoint o) {
        return date <=> o.date
    }
}
