package ru.nsu.fit.g18214.yakovlev.Model;


import ru.nsu.fit.g18214.yakovlev.Tile;

class Apple extends Fruit {

  Apple(int x, int y) {
    super(x, y);
  }

  @Override
  int getSpeedChange() {
    return 0;
  }

  @Override
  int getScoreCount() {
    return 10;
  }

  @Override
  int getSizeChange() {
    return 1;
  }

  @Override
  Tile getGameTypeForTextures() {
    return Tile.RED_FRUIT;
  }
}
