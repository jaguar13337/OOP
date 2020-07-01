package ru.nsu.fit.g18214.yakovlev.dsl.language

import ru.nsu.fit.g18214.yakovlev.dsl.engine.Config
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.*

import java.text.SimpleDateFormat

abstract class ConfigParser extends Script {

    final String dateFormat = "dd.MM.yyyy"

    Config config

    def run() {
        config = new Config()
        script()
        config
    }

    abstract void script()

    Closure student = {
        Student student = new Student()
        clMap = [with_name: {
            String name ->
                student.name = name
                clMap
        }, with_id        : {
            String id ->
                student.id = id
                clMap
        }, with_rep       : {
            String repURL ->
                student.repoUrl = repURL
                clMap
        }, to             : {
            Integer number ->
                for (Group gr : config.getGroups()) {
                    if (gr.groupId == number) {
                        gr.students.add(student)
                        break
                    }
                }
                clMap
        }]
    }

    Closure group = {
        Group group = new Group()
        clMap = [with_number: {
            Integer number ->
                group.groupId = number
                config.groups.add(group)
                clMap
        }]
    }

    Closure task = {
        Task task = new Task()
        config.tasks.add(task)
        clMap = [with_name: {
            String name ->
                task.name = name
                clMap
        }, with_number    : {
            Integer points ->
                task.points = points
                clMap
        }]
    }

    Closure control_point = {
        Checkpoint control = new Checkpoint()
        clMap = [sat: {
            Integer points ->
                control.setSat(points)
                clMap
        }, good     : {
            Integer points ->
                control.setGood(points)
                clMap
        }, exc      : {
            Integer points ->
                control.setExc(points)
                clMap
        }, on       : {
            String date ->
                control.date = new SimpleDateFormat(dateFormat).parse(date)
                clMap
                config.controls.add(control)
        }]
    }

    Closure lesson = {
        SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat)
        Lesson lesson = null
        Calendar fromDate = null
        Calendar untilDate = null
        Group group = null
        Integer daysBetweenLessons = null
        clMap = [on : {
            String date ->
                lesson = new Lesson()
                lesson.date = dateFormater.parse(date)
                if (group != null) {
                    group.lessons.add(lesson)
                }
                clMap
        }, from     : {
            String date ->
                fromDate = new GregorianCalendar()
                fromDate.setTime(dateFormater.parse(date))
                if (fromDate != null && untilDate != null && daysBetweenLessons && group != null) {
                    fillLessons(daysBetweenLessons, fromDate, untilDate, group)
                }
                clMap
        }, to       : {
            String date ->
                untilDate = new GregorianCalendar()
                untilDate.setTime(dateFormater.parse(date))
                if (fromDate != null && untilDate != null && daysBetweenLessons && group != null) {
                    fillLessons(daysBetweenLessons, fromDate, untilDate, group)
                }
                clMap
        }, every    : {
            Integer days ->
                daysBetweenLessons = days
                if (fromDate != null && untilDate != null && daysBetweenLessons && group != null) {
                    fillLessons(daysBetweenLessons, fromDate, untilDate, group)
                }
                clMap
        }, for_group: {
            Integer id ->
                for (Group groupDSL : config.groups) {
                    if (groupDSL.groupId == id) {
                        group = groupDSL
                        break
                    }
                }
                if (lesson != null) {
                    group.lessons.add(lesson)
                }
                if (fromDate != null && untilDate != null && daysBetweenLessons && group != null) {
                    fillLessons(daysBetweenLessons, fromDate, untilDate, group)
                }
                clMap
        }]
    }

    static def add(Closure what) {
        what()
    }

    Closure accept = { String taskId ->
        AcceptedTask acceptedTask = new AcceptedTask()
        for (Task task : config.tasks) {
            if (taskId == task.name) {
                acceptedTask.pointForTask = task.points
                break
            }
        }
        clMap = [for_student: {
            String id ->
                for (Group groupDSL : config.groups) {
                    for (Student studentDSL : groupDSL.students) {
                        if (studentDSL.id == id) {
                            studentDSL.acceptedTasks[taskId] = acceptedTask
                            clMap
                        }
                    }
                }
                clMap
        }, with             : {
            Integer points ->
                acceptedTask.pointForTask = points
                clMap

        }, on               : {
            String date ->
                acceptedTask.dateWhenWasAccepted = new SimpleDateFormat(dateFormat).parse(date)
                clMap

        }]
    }

    Closure present = {
        String studentId ->
            Group groupDSL = null
            Date date = null
            clMap = [on: {
                String lessonDate ->
                    date = new SimpleDateFormat(dateFormat).parse(lessonDate)
                    if (groupDSL != null) {
                        for (Lesson lessonDSL : groupDSL.lessons) {
                            if (lessonDSL.date == date) {
                                lessonDSL.attendance.put(studentId, true)
                                break
                            }
                        }
                    }
                    clMap
            }, in_group: {
                Integer groupId ->
                    for (Group group : config.groups) {
                        if (group.groupId == groupId) {
                            groupDSL = group
                            break
                        }
                    }
                    if (date != null) {
                        for (Lesson lessonDSL : groupDSL.lessons) {
                            if (lessonDSL.date == date) {
                                lessonDSL.attendance.put(studentId, true)
                                break
                            }
                        }
                    }
            }]
    }

    Closure absent = {
        String studentId ->
            Group groupDSL = null
            Date date = null
            clMap = [on: {
                String lessonDate ->
                    date = new SimpleDateFormat(dateFormat).parse(lessonDate)
                    if (groupDSL != null) {
                        for (Lesson lessonDSL : groupDSL.lessons) {
                            if (lessonDSL.date == date) {
                                lessonDSL.attendance.put(studentId, false)
                                break
                            }
                        }
                    }
                    clMap
            }, in_group: {
                Integer groupId ->
                    for (Group group : config.groups) {
                        if (group.groupId == groupId) {
                            groupDSL = group
                            break
                        }
                    }
                    if (date != null) {
                        for (Lesson lessonDSL : groupDSL.lessons) {
                            if (lessonDSL.date == date) {
                                lessonDSL.attendance.put(studentId, false)
                                break
                            }
                        }
                    }
                    clMap
            }]
    }

    Closure fillLessons = { Integer daysBetweenLessons, Calendar from, Calendar to, Group group ->
        while (from <= to) {
            Lesson lesson = new Lesson()
            lesson.date = from.getTime()
            group.lessons.add(lesson)
            from.add(Calendar.DAY_OF_MONTH, daysBetweenLessons)
        }
    }
}