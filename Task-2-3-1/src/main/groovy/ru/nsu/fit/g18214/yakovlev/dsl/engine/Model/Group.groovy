package ru.nsu.fit.g18214.yakovlev.dsl.engine.Model

class Group implements Comparable<Group> {

    List<Student> students = new ArrayList<>()
    List<Lesson> lessons = new ArrayList<>()
    Integer groupId

    @Override
    int compareTo(Group o) {
        groupId <=> o.groupId
    }
}
