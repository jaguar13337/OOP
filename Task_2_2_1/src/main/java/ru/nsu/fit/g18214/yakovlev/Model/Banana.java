package ru.nsu.fit.g18214.yakovlev.Model;

import ru.nsu.fit.g18214.yakovlev.Tile;

class Banana extends Fruit {

  Banana(int x, int y) {
    super(x, y);
  }

  @Override
  Tile getGameTypeForTextures() {
    return Tile.YELLOW_FRUIT;
  }

  @Override
  int getSpeedChange() {
    return -1;
  }

  @Override
  int getScoreCount() {
    return 5;
  }

  @Override
  int getSizeChange() {
    return -1;
  }
}
