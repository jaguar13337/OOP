package ru.nsu.fit.g18214.yakovlev

import ru.nsu.fit.g18214.yakovlev.dsl.engine.ConfigDSL
import ru.nsu.fit.g18214.yakovlev.dsl.engine.EngineDSL
import ru.nsu.fit.g18214.yakovlev.logic.Logic

class Main {

    static void main(String[] args) {


        Integer groupId = null

        String currDir = System.getProperty("user.dir")
        String cfgPath = currDir + "/config.nsu"
        String reportPath = currDir + "/report.html"

        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-c")) {
                cfgPath = args[++i]
            } else if (args[i].equalsIgnoreCase("-o")) {
                reportPath = args[++i]
            } else if (args[i].equalsIgnoreCase("report")) {
                groupId = Integer.parseInt(args[++i])
            }
        }

        if (groupId == null) {
            println "Введите необходимю команду, " +
                    "а также номер группы, для которой эту команду нужно исполнить"
        } else {

            try {
                ConfigDSL configDSL = EngineDSL.executeDSL(cfgPath)
                Logic logic = new Logic(configDSL)
                File file = new File(reportPath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter writer = new FileWriter(file);

                logic.performanceReport(groupId, writer)
                logic.attendanceReport(groupId, writer)

                writer.close();
            } catch (IllegalArgumentException ignored) {
                println "Введена неправильная группа: " + groupId
            } catch (FileNotFoundException ignored) {
                println "Невозможно найти файл: " + cfgPath
            } catch (IOException ignored) {
                println "Невозможно открыть файл: " + reportPath
            }
        }
    }

}
