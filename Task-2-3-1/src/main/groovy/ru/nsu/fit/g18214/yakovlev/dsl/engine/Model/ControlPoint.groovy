package ru.nsu.fit.g18214.yakovlev.dsl.engine.Model

class ControlPoint implements Comparable<ControlPoint> {
    Date date
    Integer[] grades = new Integer[3]

    void setPointsForSatGrade(Integer points) {
        grades[0] = points
    }

    void setPointsForGoodGrade(Integer points) {
        grades[1] = points
    }

    void setPointsForExcGrade(Integer points) {
        grades[2] = points
    }

    Integer getPointsForSatGrade() {
        grades[0]
    }

    Integer getPointsForGoodGrade() {
        grades[1]
    }

    Integer getPointsForExcGrade() {
        grades[2]
    }

    @Override
    int compareTo(ControlPoint o) {
        return date <=> o.date
    }
}
