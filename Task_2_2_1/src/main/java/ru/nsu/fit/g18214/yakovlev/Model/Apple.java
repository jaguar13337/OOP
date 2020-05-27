package ru.nsu.fit.g18214.yakovlev.Model;


import ru.nsu.fit.g18214.yakovlev.TextureType;

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
  TextureType getGameTypeForTextures() {
    return TextureType.RED_FRUIT;
  }
}
