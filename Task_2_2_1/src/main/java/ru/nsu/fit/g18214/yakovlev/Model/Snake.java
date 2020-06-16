package ru.nsu.fit.g18214.yakovlev.Model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Snake {
  private Deque<Coordinate> snakeBody;
  private int score;
  private int speed;
  private static Directions direction;
  private static Directions prevDir;

  void setDirection(Directions dir) {
    prevDir = direction;
    direction = dir;
  }

  Snake(int currX, int currY) {
    score = 0;
    speed = 5;
    snakeBody = new LinkedList<>();
    addBody(currX, currY);
    direction = Directions.UP;
  }

  int getScore() {
    return score;
  }

  int getSpeed() {
    return speed;
  }


  List<Coordinate> eatFruit(Fruit fruit) {
    List<Coordinate> mustBeRemoved = new ArrayList<>();
    if (speed + fruit.getSpeedChange() >= 3) {
      speed += fruit.getSpeedChange();
    } else {
      speed = 3;
    }
    if (fruit.getSizeChange() < 0) {
      for (int i = 0; i < -fruit.getSizeChange(); i++) {
        if (snakeBody.size() == 1) {
          break;
        }
        mustBeRemoved.add(snakeBody.removeLast());
      }
    } else {
      for (int i = 0; i < fruit.getSizeChange(); i++) {
        addBody(getSnakeHeadX(), getSnakeHeadY());
      }
    }
    score += fruit.getScoreCount();
    return mustBeRemoved;
  }

  Coordinate move() {
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
    return snakeBody.removeLast();
  }

  private void addBody(int currX, int currY) {
    Coordinate coordinate = new Coordinate(currX, currY);
    if (snakeBody.size() > 0 && (currX == getSnakeHeadX() || currY == getSnakeHeadY())) {
      snakeBody.addFirst(coordinate);
    } else {
      snakeBody.addLast(coordinate);
    }
  }

  int getSnakeHeadX() {
    return snakeBody.getFirst().getX();
  }

  int getSnakeHeadY() {
    return snakeBody.getFirst().getY();
  }

  int getSnakeTailX() {
    return snakeBody.getLast().getX();
  }

  int getSnakeTailY() {
    return snakeBody.getLast().getY();
  }


  Deque<Coordinate> getSnakeBody() {
    return snakeBody;
  }
}
