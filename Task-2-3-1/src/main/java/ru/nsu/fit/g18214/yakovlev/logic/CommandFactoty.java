package ru.nsu.fit.g18214.yakovlev.logic;

import java.util.Map;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Config;

public interface CommandFactoty {
  Command findCommand(Config config, Map<String, String> args);
}
