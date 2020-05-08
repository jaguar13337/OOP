package ru.nsu.fit.g18214.yakovlev.Model;

class Cranberry extends Fruit {

  Cranberry(int x, int y) {
    super(x, y);
  }

  @Override
  TypeForTextures getGameTypeForTextures() {
    return TypeForTextures.VIOLET_FRUIT;
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
