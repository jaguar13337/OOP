package ru.nsu.fit.g18214.yakovlev;

import javafx.scene.paint.Paint;

public class Apple implements Fruit {

  private int x;
  private int y;

  Apple(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public Paint getColor() {
    return Paint.valueOf("red");
  }

  @Override
  public int eat() {
    return 1;
  }
}
