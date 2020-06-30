package ru.nsu.fit.g18214.yakovlev

import ru.nsu.fit.g18214.yakovlev.dsl.engine.Config
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Engine
import ru.nsu.fit.g18214.yakovlev.logic.Command
import ru.nsu.fit.g18214.yakovlev.logic.Logic

class Main {

    static void main(String[] args) {


        String currDir = System.getProperty("user.dir")
        String cfgPath = currDir + "/config.nsu"
        String reportPath = currDir + "/report.html"

        Map<String, String> arguments = new HashMap<>()
        if (args.length == 0) {
            println("Сначала введите запрос из перечисленных ниже без '-'. Далее - перечислите необходимые аргументы.\n" +
                    "-o \"path/to/report\" -> Путь, куда записать отчет. (Если не указан - сохранит в текущую директорию)\n" +
                    "-c \"path/to/config\" -> Путь до конфигурационного файла.\n" +
                    "-g \" groupID\" -> Номер группы, для которой выполняется запрос.\n" +
                    "-s \" studentID\" -> ID студента, для которого выполняется запрос.\n" +
                    "-t \" taskID\" -> ID задачи, для которой выполняется запрос.\n" +
                    "fullReport -> Полный отчет с двумя таблицами для группы" +
                    " После этого обязательно перечислить ключ -g\n" +
                    "attendanceOnly -> Отчет по посещаемости студентами группы\n" +
                    " После этого обязательно перечислить ключ -g\n" +
                    "performanceOnly -> Отчет по успеваемости студентами группы\n" +
                    " После этого обязательно перечислить ключ -g\n" +
                    "codestyle -> Информация по следованию кода задачи принятому кодстайлу." +
                    " После этого обязательно перечислить ключи -s, -g и -t.\n" +
                    "build -> Информация по сборке данной задачи." +
                    " После этого обязательно перечислить ключи -s, -g и -t.\n" +
                    "tests -> Информация по прохождению тестов данной задачи." +
                    " После этого обязательно перечислить ключи -s, -g и -t.\n" +
                    "docs -> Информация по генерации документации данной задаачи." +
                    " После этого обязательно перечислить ключи -s, -g и -t.\n")
        } else {
            try {
                arguments.put("task", args[0])
                for (int i = 1; i < args.length; i++) {
                    if (args[i].equalsIgnoreCase("-c")) {
                        cfgPath = args[++i]
                    } else if (args[i].equalsIgnoreCase("-o")) {
                        reportPath = args[++i]
                    } else {
                        arguments.put(args[i], args[++i]);
                    }
                }

                Config configDSL = Engine.executeDSL(cfgPath)

                File file = new File(reportPath)
                if (!file.exists()) {
                    file.createNewFile()
                }
                FileWriter writer = new FileWriter(file);
                Logic logic = new Logic(writer)

                Command command = logic.findCommand(configDSL, arguments)
                if (command == null) {
                    println("Допущена ошибка в формировании запроса. Проверьте введенные данные.")
                } else {
                    command.runCommand()
                }

                writer.close();
            } catch (IllegalArgumentException ignored) {
                println "Введена неправильная группа: " + arguments.get("-g")
            } catch (FileNotFoundException ignored) {
                println "Невозможно найти файл: " + cfgPath
            } catch (IOException ignored) {
                println "Невозможно открыть файл: " + reportPath
            }
        }
    }
}