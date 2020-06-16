package ru.nsu.fit.g18214.yakovlev;

import ru.nsu.fit.g18214.yakovlev.Model.State;

public interface Observer {
  void stateChanged(State state);

  void tickEnd();
}
