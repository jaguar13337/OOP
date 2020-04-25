package ru.nsu.fit.g18214.yakovlev;

import javafx.scene.paint.Paint;

class Cranberry extends Fruit{

  Cranberry(int x, int y) {
    super(x, y);
  }

  @Override
  Paint getColor() {
    return Paint.valueOf("violet");
  }

  @Override
  int getAddedSpeed() {
    return 1;
  }

  @Override
  int getScoreCount() {
    return 25;
  }

  @Override
  int getSizeAdded() {
    return 0;
  }
}
