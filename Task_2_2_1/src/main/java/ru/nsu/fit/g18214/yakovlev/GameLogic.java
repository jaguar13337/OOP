package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Paint;

class GameLogic {

  private static final int CELL_CNT = 25;
  private static final int timerToTick = 1000000000;
  private static Random random = new Random();
  private static final int fruitCount = 1;
  private static Snake snake;
  private static List<Fruit> fruits;
  private static State gameState = State.NOTHING;
  private static GameObject[][] gameObjects;
  private static List<Directions> dirsQueue;


  static GameObject[][] getGameObjects() {
    return gameObjects;
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
    gameObjects = new GameObject[CELL_CNT][CELL_CNT];
    for (int i = 0; i < CELL_CNT; i++) {
      for (int j = 0; j < CELL_CNT; j++) {
        gameObjects[i][j] = new GameObject(i, j);
        if ((i + j) % 2 == 1) {
          gameObjects[i][j].setDefFill(Paint.valueOf("green"));
        } else {
          gameObjects[i][j].setDefFill(Paint.valueOf("green"));
        }
      }
    }
  }

  private static void newFood() {
    for (int i = fruits.size(); i < fruitCount; i++) {
      while (true) {
        int x = random.nextInt(CELL_CNT);
        int y = random.nextInt(CELL_CNT);
        Fruit fruit = new Apple(x, y);
        if (gameObjects[x][y].getType() == ObjectType.SNAKE) {
          continue;
        }
        fruits.add(fruit);
        gameObjects[x][y].setType(ObjectType.FOOD, fruits.get(fruits.size() - 1).getColor());
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
      gameObjects[coord.getX()][coord.getY()].setType(ObjectType.SNAKE, snake.getColor());
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

      if (gameObjects[snake.getSnakeHeadX()][snake.getSnakeHeadY()].getType() == ObjectType.FOOD) {
        for (Fruit fruit : fruits) {
          if (fruit.getX() == snake.getSnakeHeadX() && fruit.getY() == snake.getSnakeHeadY()) {
            snake.eatFruit(fruit);
            fruits.remove(fruit);
            break;
          }
        }
        newFood();
      }
      gameObjects[dels.getX()][dels.getY()].setType();

      gameObjects[snake.getSnakeHeadX()][snake.getSnakeHeadY()].setType(ObjectType.SNAKE, snake.getColor());

    }
  }

}
