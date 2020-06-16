package ru.nsu.fit.g18214.yakovlev.dsl.engine

class LessonDSL implements Comparable<LessonDSL> {
    Map<String, Boolean> attendance = new HashMap<>()
    Date date

    @Override
    int compareTo(LessonDSL o) {
        date <=> o.date
    }
}
