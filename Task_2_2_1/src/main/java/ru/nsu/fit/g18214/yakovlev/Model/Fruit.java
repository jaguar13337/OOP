package ru.nsu.fit.g18214.yakovlev.Model;


import ru.nsu.fit.g18214.yakovlev.Tile;

abstract class Fruit {

  private int x;
  private int y;

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  Fruit(int x, int y) {
    this.x = x;
    this.y = y;
  }

  abstract int getSpeedChange();

  abstract int getScoreCount();

  abstract int getSizeChange();

  abstract Tile getGameTypeForTextures();
}
