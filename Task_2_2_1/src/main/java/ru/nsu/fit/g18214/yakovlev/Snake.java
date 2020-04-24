package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Paint;

class Snake {
  private List<Coord> snakeBody;
  private int score = 0;
  private int speed = 5;
  private int len = 1;
  private static Directions direction;
  private static Directions prevDir;

  void setDirection(Directions dir) {
    prevDir = direction;
    direction = dir;
  }

  Snake(int currX, int currY) {
    snakeBody = new ArrayList<>();
    for (int i = 0; i < len; i++) {
      addBody(currX, currY);
    }
    direction = Directions.UP;
  }

  public int getScore() {
    return score;
  }

  public int getSpeed() {
    return speed;
  }

  public int getLen() {
    return len;
  }

  void eatFruit(Fruit fruit) {
    speed += fruit.getAddedSpeed();
    len += fruit.getSizeAdded();
    for (int i = 0; i < fruit.getSizeAdded(); i++) {
      addBody(getSnakeHeadX(), getSnakeHeadY());
    }
    score += fruit.getScoreCount();
  }

  Coord move() {
    switch (direction) {
      case UP:
        if (prevDir != Directions.DOWN) {
          addBody(getSnakeHeadX(), getSnakeHeadY() - 1);
        } else {
          addBody(getSnakeHeadX(), getSnakeHeadY() + 1);
          direction = Directions.DOWN;
        }
        break;
      case DOWN:
        if (prevDir != Directions.UP) {
          addBody(getSnakeHeadX(), getSnakeHeadY() + 1);
        } else {
          addBody(getSnakeHeadX(), getSnakeHeadY() - 1);
          direction = Directions.UP;
        }
        break;
      case LEFT:
        if (prevDir != Directions.RIGHT) {
          addBody(getSnakeHeadX() - 1, getSnakeHeadY());
        } else {
          addBody(getSnakeHeadX() + 1, getSnakeHeadY());
          direction = Directions.RIGHT;
        }
        break;
      case RIGHT:
        if (prevDir != Directions.LEFT) {
          addBody(getSnakeHeadX() + 1, getSnakeHeadY());
        } else {
          addBody(getSnakeHeadX() - 1, getSnakeHeadY());
          direction = Directions.LEFT;
        }
        break;
    }
    return snakeBody.remove(snakeBody.size() - 1);
  }

  void addBody(int currX, int currY) {
    Coord coord = new Coord(currX, currY);
    if (snakeBody.size() > 0 && (currX == getSnakeHeadX() || currY == getSnakeHeadY())) {
      snakeBody.add(0, coord);
    } else {
      snakeBody.add(coord);
    }
  }

  int getSnakeHeadX() {
    return snakeBody.get(0).getX();
  }

  int getSnakeHeadY() {
    return snakeBody.get(0).getY();
  }

  int getSnakeBodyX(int i) {
    return snakeBody.get(i).getX();
  }

  int getSnakeBodyY(int i) {
    return snakeBody.get(i).getY();
  }

  List<Coord> getSnakeBody() {
    return snakeBody;
  }

  Paint getColor() {
    return Paint.valueOf("saddlebrown");
  }

}
