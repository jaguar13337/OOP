package ru.nsu.fit.g18214.yakovlev.Model;


class Apple extends Fruit {

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

  Apple(int x, int y) {
    super(x, y);
  }

  @Override
  TypeForTextures getGameTypeForTextures() {
    return TypeForTextures.RED_FRUIT;
  }
}
