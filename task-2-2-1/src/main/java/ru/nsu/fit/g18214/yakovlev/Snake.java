package ru.nsu.fit.g18214.yakovlev;

public class Snake {
  private int currX;
  private int currY;
  private int length;

  public Snake(int currX, int currY) {
    this.currX = currX;
    this.currY = currY;
    this.length = 1;
  }

  void goUp() {
    currY++;
  }

  void goDown() {
    currY--;
  }

  void goLeft() {
    currX--;
  }

  void goRight() {
    currX++;
  }

  void eatApple(Fruit fruit) {
    length += fruit.eat();
  }

  public int getCurrX() {
    return currX;
  }

  public int getCurrY() {
    return currY;
  }

  public int getLength() {
    return length;
  }
}
