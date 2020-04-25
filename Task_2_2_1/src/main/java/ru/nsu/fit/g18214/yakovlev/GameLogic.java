package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Paint;

class GameLogic {

  private static final int CELL_CNT = 25;
  private static final int timerToTick = 1000000000;
  private static Random random = new Random();
  private static final int fruitCount = 4;
  private static Snake snake;
  private static List<Fruit> fruits;
  private static State gameState = State.NOTHING;
  private static GameObject[][] gameObjectsField;
  private static List<Directions> dirsQueue;


  static GameObject[][] getGameObjectsField() {
    return gameObjectsField;
  }

  static void addDir(Directions dir) {
    if (dirsQueue.size() == 0 || dirsQueue.get(dirsQueue.size() - 1) != dir) {
      dirsQueue.add(dir);
    }
  }

  static int getCellCnt() {
    return CELL_CNT;
  }

  static State getState() {
    return gameState;
  }

  static Integer getScore() {
    return snake.getScore();
  }

  static void changeState(State newState) {
    if (gameState.equals(newState)) {
      gameState = State.NOTHING;
    } else {
      gameState = newState;
    }
  }

  static int getTimerToTick() {
    return timerToTick / snake.getSpeed();
  }

  static void initializeField() {
    gameObjectsField = new GameObject[CELL_CNT][CELL_CNT];
    for (int i = 0; i < CELL_CNT; i++) {
      for (int j = 0; j < CELL_CNT; j++) {
        gameObjectsField[i][j] = new GameObject(i, j);
        if ((i + j) % 2 == 1) {
          gameObjectsField[i][j].setDefFill(Paint.valueOf("green"));
        } else {
          gameObjectsField[i][j].setDefFill(Paint.valueOf("green"));
        }
      }
    }
  }

  private static void newFood() {
    for (int i = fruits.size(); i < fruitCount; i++) {
      while (true) {
        int x = random.nextInt(CELL_CNT);
        int y = random.nextInt(CELL_CNT);
        Fruit fruit = null;
        switch (random.nextInt(3)) {
          case 0:
            fruit = new Apple(x, y);
            break;
          case 1:
            fruit = new Banana(x,y);
            break;
          case 2:
            fruit = new Cranberry(x,y);
            break;
        }
        if (gameObjectsField[x][y].getType() != ObjectType.EMPTY) {
          continue;
        }
        fruits.add(fruit);
        gameObjectsField[x][y].setType(ObjectType.FOOD, fruits.get(fruits.size() - 1).getColor());
        break;
      }
    }
  }

  static void gameInit() {
    snake = new Snake(CELL_CNT / 2, CELL_CNT / 2);
    fruits = new ArrayList<>();
    dirsQueue = new ArrayList<>();
    gameState = State.NOTHING;
    for (Coord coord : snake.getSnakeBody()) {
      gameObjectsField[coord.getX()][coord.getY()].setType(ObjectType.SNAKE, snake.getColor());
    }
    newFood();
  }

  static void gameTick() {
    if (gameState == State.NOTHING) {
      if (dirsQueue.size() > 0) {
        snake.setDirection(dirsQueue.remove(0));
      }
      Coord dels = snake.move();

      if (snake.getSnakeHeadY() < 0 || snake.getSnakeHeadX() < 0 ||
        snake.getSnakeHeadX() >= CELL_CNT || snake.getSnakeHeadY() >= CELL_CNT) {
        gameState = State.GAMEOVER;
        return;
      }

      for (int i = 1; i < snake.getSnakeBody().size(); i++) {
        if (snake.getSnakeHeadX() == snake.getSnakeBodyX(i) &&
          snake.getSnakeHeadY() == snake.getSnakeBodyY(i)) {
          gameState = State.GAMEOVER;
        }
      }

      if (gameObjectsField[snake.getSnakeHeadX()][snake.getSnakeHeadY()].getType() == ObjectType.FOOD) {
        for (Fruit fruit : fruits) {
          if (fruit.getX() == snake.getSnakeHeadX() && fruit.getY() == snake.getSnakeHeadY()) {
            List<Coord> mustBeRemoved = snake.eatFruit(fruit);
            for (Coord coord: mustBeRemoved) {
              gameObjectsField[coord.getX()][coord.getY()].setType();
            }
            fruits.remove(fruit);
            break;
          }
        }
        newFood();
      }
      gameObjectsField[dels.getX()][dels.getY()].setType();

      gameObjectsField[snake.getSnakeHeadX()][snake.getSnakeHeadY()].setType(ObjectType.SNAKE, snake.getColor());

    }
  }

}
