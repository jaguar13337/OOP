package ru.nsu.fit.g18214.yakovlev;


import javafx.scene.paint.Paint;

abstract class Fruit{

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

  abstract Paint getColor();
  abstract int getAddedSpeed();
  abstract int getScoreCount();
  abstract int getSizeAdded();
}
