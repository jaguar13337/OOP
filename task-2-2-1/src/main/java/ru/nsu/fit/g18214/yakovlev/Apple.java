package ru.nsu.fit.g18214.yakovlev;

import javafx.scene.paint.Paint;

public class Apple implements Fruit {

  @Override
  public Paint getColor() {
    return Paint.valueOf("red");
  }

  @Override
  public int eat() {
    return 1;
  }
}
