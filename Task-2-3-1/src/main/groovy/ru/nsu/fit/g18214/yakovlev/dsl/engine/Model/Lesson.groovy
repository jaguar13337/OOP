package ru.nsu.fit.g18214.yakovlev.dsl.engine.Model

class Lesson implements Comparable<Lesson> {
    Map<String, Boolean> attendance = new HashMap<>()
    Date date

    @Override
    int compareTo(Lesson o) {
        date <=> o.date
    }
}
