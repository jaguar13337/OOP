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
  private List<Coord> mustBeRemoved;

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
    mustBeRemoved = new ArrayList<>();
  }

  int getScore() {
    return score;
  }

  int getSpeed() {
    return speed;
  }

  public int getLen() {
    return len;
  }

  List<Coord> eatFruit(Fruit fruit) {
    mustBeRemoved.clear();
    if (speed + fruit.getAddedSpeed() >= 1) {
      speed += fruit.getAddedSpeed();
    } else {
      speed = 1;
    }
    if (fruit.getSizeAdded() < 0) {
      for (int i = 0; i < -fruit.getSizeAdded(); i++) {
        if (len == 1) {
          break;
        }
        mustBeRemoved.add(snakeBody.remove(snakeBody.size() - 1));
        len--;
      }
    } else {
      len += fruit.getSizeAdded();
      for (int i = 0; i < fruit.getSizeAdded(); i++) {
        addBody(getSnakeHeadX(), getSnakeHeadY());
      }
    }
    score += fruit.getScoreCount();
    return mustBeRemoved;
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

  private void addBody(int currX, int currY) {
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
