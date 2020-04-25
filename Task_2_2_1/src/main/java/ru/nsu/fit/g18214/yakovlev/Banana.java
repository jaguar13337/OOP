package ru.nsu.fit.g18214.yakovlev;

import javafx.scene.paint.Paint;

class Banana extends Fruit {

  Banana(int x, int y) {
    super(x, y);
  }

  @Override
  Paint getColor() {
    return Paint.valueOf("yellow");
  }

  @Override
  int getAddedSpeed() {
    return -1;
  }

  @Override
  int getScoreCount() {
    return 5;
  }

  @Override
  int getSizeAdded() {
    return -1;
  }
}
