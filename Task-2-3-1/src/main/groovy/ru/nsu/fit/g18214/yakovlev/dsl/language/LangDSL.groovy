package ru.nsu.fit.g18214.yakovlev.dsl.language

import ru.nsu.fit.g18214.yakovlev.dsl.engine.*

import java.text.SimpleDateFormat

abstract class LangDSL extends Script {

    final String dateFormat = "dd.MM.yyyy"

    ConfigDSL config

    def run() {
        config = new ConfigDSL()
        script()
        config
    }

    abstract void script()

    Closure student = {
        StudentDSL student = new StudentDSL()
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
                for (GroupDSL gr : config.getGroups()) {
                    if (gr.groupId == number) {
                        gr.students.add(student)
                        break
                    }
                }
                clMap
        }]
    }

    Closure group = {
        GroupDSL group = new GroupDSL()
        clMap = [with_number: {
            Integer number ->
                group.groupId = number
                config.groups.add(group)
                clMap
        }]
    }

    Closure task = {
        TaskDSL task = new TaskDSL()
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
        ControlPointDSL control = new ControlPointDSL()
        clMap = [sat: {
            Integer points ->
                control.setPointsForSatGrade(points)
                clMap
        }, good     : {
            Integer points ->
                control.setPointsForGoodGrade(points)
                clMap
        }, exc      : {
            Integer points ->
                control.setPointsForExcGrade(points)
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
        LessonDSL lesson = null
        Calendar fromDate = null
        Calendar untilDate = null
        GroupDSL group = null
        Integer daysBetweenLessons = null
        clMap = [on : {
            String date ->
                lesson = new LessonDSL()
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
                for (GroupDSL groupDSL : config.groups) {
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
        AcceptedTaskDSL acceptedTask = new AcceptedTaskDSL()
        for (TaskDSL task : config.tasks) {
            if (taskId == task.name) {
                acceptedTask.pointForTask = task.points
                break
            }
        }
        clMap = [for_student: {
            String id ->
                for (GroupDSL groupDSL : config.groups) {
                    for (StudentDSL studentDSL : groupDSL.students) {
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
            GroupDSL groupDSL = null
            Date date = null
            clMap = [on: {
                String lessonDate ->
                    date = new SimpleDateFormat(dateFormat).parse(lessonDate)
                    if (groupDSL != null) {
                        for (LessonDSL lessonDSL : groupDSL.lessons) {
                            if (lessonDSL.date == date) {
                                lessonDSL.attendance.put(studentId, true)
                                break
                            }
                        }
                    }
                    clMap
            }, in_group : {
                Integer groupId ->
                    for (GroupDSL group : config.groups) {
                        if (group.groupId == groupId) {
                            groupDSL = group
                            break
                        }
                    }
                    if (date != null) {
                        for (LessonDSL lessonDSL : groupDSL.lessons) {
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
            GroupDSL groupDSL = null
            Date date = null
            clMap = [on: {
                String lessonDate ->
                    date = new SimpleDateFormat(dateFormat).parse(lessonDate)
                    if (groupDSL != null) {
                        for (LessonDSL lessonDSL : groupDSL.lessons) {
                            if (lessonDSL.date == date) {
                                lessonDSL.attendance.put(studentId, false)
                                break
                            }
                        }
                    }
                    clMap
            }, in_group : {
                Integer groupId ->
                    for (GroupDSL group : config.groups) {
                        if (group.groupId == groupId) {
                            groupDSL = group
                            break
                        }
                    }
                    if (date != null) {
                        for (LessonDSL lessonDSL : groupDSL.lessons) {
                            if (lessonDSL.date == date) {
                                lessonDSL.attendance.put(studentId, false)
                                break
                            }
                        }
                    }
                    clMap
            }]
    }

    Closure fillLessons = { Integer daysBetweenLessons, Calendar from, Calendar to, GroupDSL group ->
        while (from <= to) {
            LessonDSL lesson = new LessonDSL()
            lesson.date = from.getTime()
            group.lessons.add(lesson)
            from.add(Calendar.DAY_OF_MONTH, daysBetweenLessons)
        }
    }
}