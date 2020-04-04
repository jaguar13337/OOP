package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;

public class Snake {
  private List<Rectangle> snakeBody;


  public Snake(int currX, int currY) {
    snakeBody = new ArrayList<>();
    addBody(currX, currY);
  }

  void addBody(int currX, int currY) {
    Rectangle rectangle = new Rectangle();
    rectangle.setX(currX);
    rectangle.setY(currY);
    if (snakeBody.size() > 0 && (currX == getSnakeHeadX() || currY == getSnakeHeadY())) {
      snakeBody.add(0, rectangle);
    } else {
      snakeBody.add(rectangle);
    }
  }

  int getSnakeHeadX() {
    return (int) snakeBody.get(0).getX();
  }

  int getSnakeHeadY() {
    ;
    return (int) snakeBody.get(0).getY();
  }

  double getSnakeBodyX(int i) {
    return snakeBody.get(i).getX();
  }


  double getSnakeBodyY(int i) {
    return snakeBody.get(i).getY();
  }


  public List<Rectangle> getSnakeBody() {
    return snakeBody;
  }

}
