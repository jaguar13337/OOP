package ru.nsu.fit.g18214.yakovlev;


import javafx.scene.paint.Paint;

class Apple extends Fruit {

  @Override
  int getAddedSpeed() {
    return 0;
  }

  @Override
  int getScoreCount() {
    return 10;
  }

  @Override
  int getSizeAdded() {
    return 1;
  }

  Apple(int x, int y) {
    super(x, y);
  }

  @Override
  Paint getColor() {
    return Paint.valueOf("red");
  }
}
