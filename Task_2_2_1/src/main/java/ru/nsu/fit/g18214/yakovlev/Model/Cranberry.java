package ru.nsu.fit.g18214.yakovlev.Model;

import ru.nsu.fit.g18214.yakovlev.Tile;

class Cranberry extends Fruit {

  Cranberry(int x, int y) {
    super(x, y);
  }

  @Override
  Tile getGameTypeForTextures() {
    return Tile.VIOLET_FRUIT;
  }

  @Override
  int getSpeedChange() {
    return 1;
  }

  @Override
  int getScoreCount() {
    return 25;
  }

  @Override
  int getSizeChange() {
    return 0;
  }
}
