package ru.nsu.fit.g18214.yakovlev.dsl.engine

class StudentDSL {

    String id
    String name
    String repoUrl
    Map<String, AcceptedTaskDSL> acceptedTasks = new HashMap<>()


}