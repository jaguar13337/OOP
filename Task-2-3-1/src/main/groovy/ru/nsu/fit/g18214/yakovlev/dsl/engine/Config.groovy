package ru.nsu.fit.g18214.yakovlev.dsl.engine

import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Checkpoint
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Group
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Task

class Config {

    Set<Group> groups = new TreeSet<>()
    List<Task> tasks = new ArrayList<>()
    Set<Checkpoint> controls = new TreeSet<>()
}
