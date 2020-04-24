package ru.nsu.fit.g18214.yakovlev;


import javafx.scene.paint.Paint;

public class Apple extends Fruit{

  @Override
  public int getAddedSpeed() {
    return 0;
  }

  @Override
  public int getScoreCount() {
    return 10;
  }

  @Override
  public int getSizeAdded() {
    return 1;
  }

  Apple(int x, int y) {
    super(x, y);
  }

  @Override
  Paint getColor() {
    return Paint.valueOf("yellow");
  }
}
