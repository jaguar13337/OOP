package ru.nsu.fit.g18214.yakovlev.dsl.engine.Model

import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.AcceptedTask

class Student {

    String id
    String name
    String repoUrl
    Map<String, AcceptedTask> acceptedTasks = new HashMap<>()


}