package ru.nsu.fit.g18214.yakovlev.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {


  private final int CELL_CNT = 25;
  private final int TIME_TO_TICK = 1000000000;
  private final int FRUIT_COUNT = 4;

  private Random random = new Random();
  private Snake snake;
  private List<Fruit> fruits;
  private State gameState = State.NOTHING;
  private List<Directions> dirsQueue;
  private GameField gameField;
  private Thread thread;


  public TypeForTextures getCellTypeForTextures(int x, int y) {
    return gameField.getTypeForTextures(x, y);
  }

  public void addDir(Directions dir) {
    if (dirsQueue.size() == 0 || dirsQueue.get(dirsQueue.size() - 1) != dir) {
      dirsQueue.add(dir);
    }
  }

  public int getCellCnt() {
    return CELL_CNT;
  }

  public State getState() {
    return gameState;
  }

  public Integer getScore() {
    return snake.getScore();
  }

  public void changeState(State newState) {
    if (gameState.equals(newState)) {
      gameState = State.NOTHING;
    } else {
      gameState = newState;
    }
  }

  public int getTimeToTick() {
    return TIME_TO_TICK / snake.getSpeed();
  }

  public void initializeField() {
    gameField = new GameField(CELL_CNT);
    gameField.initializeField();
  }

  private void newFood() {
    for (int i = fruits.size(); i < FRUIT_COUNT; i++) {
      while (true) {
        int x = random.nextInt(CELL_CNT);
        int y = random.nextInt(CELL_CNT);
        Fruit fruit = null;
        switch (random.nextInt(3)) {
          case 0:
            fruit = new Apple(x, y);
            break;
          case 1:
            fruit = new Banana(x, y);
            break;
          case 2:
            fruit = new Cranberry(x, y);
            break;
        }
        if (gameField.getCellType(x, y) != ObjectType.EMPTY) {
          continue;
        }
        fruits.add(fruit);
        assert fruit != null;
        gameField.setCellType(x, y, ObjectType.FOOD, fruit.getGameTypeForTextures());
        break;
      }
    }
  }

  public void gameInit() {
    snake = new Snake(CELL_CNT / 2, CELL_CNT / 2);
    fruits = new ArrayList<>();
    dirsQueue = new ArrayList<>();
    gameState = State.NOTHING;
    setSnake();
    newFood();
    thread = new Thread(this::gameTick);
  }

  private void setSnake() {
    for (Coordinate coordinate : snake.getSnakeBody()) {
      gameField.setCellType(coordinate.getX(), coordinate.getY(),
        ObjectType.SNAKE, TypeForTextures.SNAKE_BODY);
    }
    gameField.setCellType(snake.getSnakeTailX(), snake.getSnakeTailY(),
      ObjectType.SNAKE, TypeForTextures.SNAKE_TAIL);
    gameField.setCellType(snake.getSnakeHeadX(), snake.getSnakeHeadY(),
      ObjectType.SNAKE, TypeForTextures.SNAKE_HEAD);
  }

  public void gameTick() {
    while (!Thread.interrupted()) {
      if (gameState == State.NOTHING) {
        if (dirsQueue.size() > 0) {
          snake.setDirection(dirsQueue.remove(0));
        }

        Coordinate dels = snake.move();

        if (snake.getSnakeHeadY() < 0 || snake.getSnakeHeadX() < 0 ||
          snake.getSnakeHeadX() >= CELL_CNT || snake.getSnakeHeadY() >= CELL_CNT) {
          gameState = State.GAMEOVER;
          return;
        }

        boolean check = false;
        for (Coordinate coordinate : snake.getSnakeBody()) {
          if (check && snake.getSnakeHeadX() == coordinate.getX() &&
            snake.getSnakeHeadY() == coordinate.getY()) {
            gameState = State.GAMEOVER;
          }
          check = true;
        }

        if (gameField.getCellType(snake.getSnakeHeadX(), snake.getSnakeHeadY()) == ObjectType.FOOD) {
          for (Fruit fruit : fruits) {
            if (fruit.getX() == snake.getSnakeHeadX() && fruit.getY() == snake.getSnakeHeadY()) {
              List<Coordinate> mustBeRemoved = snake.eatFruit(fruit);
              for (Coordinate coordinate : mustBeRemoved) {
                gameField.makeCellEmpty(coordinate.getX(), coordinate.getY());
              }
              fruits.remove(fruit);
              break;
            }
          }
          newFood();
        }
        gameField.makeCellEmpty(dels.getX(), dels.getY());
        setSnake();
        try {
          Thread.sleep(250 - snake.getSpeed() * 10);
        } catch (InterruptedException e) {
          return;
        }
      }
    }
  }

  public void start() {
    thread.start();
  }

  public void stop() {
    if (thread != null) {
      thread.interrupt();
      try {
        thread.join();
      } catch (InterruptedException e) {
        assert false;
      }
    }
  }

}
