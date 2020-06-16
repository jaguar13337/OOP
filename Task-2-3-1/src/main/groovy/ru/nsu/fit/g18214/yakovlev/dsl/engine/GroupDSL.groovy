package ru.nsu.fit.g18214.yakovlev.dsl.engine

class GroupDSL implements Comparable<GroupDSL> {

    List<StudentDSL> students = new ArrayList<>()
    List<LessonDSL> lessons = new ArrayList<>()
    Integer groupId

    @Override
    int compareTo(GroupDSL o) {
        groupId <=> o.groupId
    }
}
