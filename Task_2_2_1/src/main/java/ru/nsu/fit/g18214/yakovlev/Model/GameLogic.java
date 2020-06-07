package ru.nsu.fit.g18214.yakovlev.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import ru.nsu.fit.g18214.yakovlev.Observer;
import ru.nsu.fit.g18214.yakovlev.Tile;


/**
 * This is the main Model class, which is responsible for the whole game state and its logic.
 */
public class GameLogic {

  private final int CELL_CNT = 25;
  private final int FRUIT_COUNT = 4;
  private final Object lock = new Object();
  private Random random = new Random();
  private Snake snake;
  private List<Fruit> fruits;
  private volatile State gameState = State.GAME_RUNNING;
  private boolean gameOver;
  private ConcurrentLinkedQueue<Directions> dirsQueue;
  private GameField gameField;
  private Thread thread;
  private Observer observer;

  public GameLogic(Observer observer) {
    this.observer = observer;
  }

  /**
   * Returns type of gameField cell with given coordinates,
   * which should be used for coloring this cell in the controller.
   *
   * @param x first coordinate
   * @param y second coordinate
   * @return Type, which is used for coloring it in the controller.
   */
  public Tile getCellTextureType(int x, int y) {
    return gameField.getTypeForTextures(x, y);
  }

  /**
   * Added direction after the users input to the directions queue.
   *
   * @param dir given direction.
   */
  public void addDir(Directions dir) {
    if (dirsQueue.size() == 0 || dirsQueue.peek() != dir) {
      dirsQueue.add(dir);
    }
  }

  /**
   * Returns count of cells on the field. Use for the cell size counting.
   *
   * @return count of cells.
   */
  public int getCellCnt() {
    return CELL_CNT;
  }

  /**
   * Returns current game state for the controller. Used for the showing some additional labels.
   *
   * @return current game state.
   */
  public State getState() {
    synchronized (lock) {
      return gameState;
    }
  }

  /**
   * Returns current players game score. Used in controller for showing it to the user.
   *
   * @return game score.
   */
  public Integer getScore() {
    return snake.getScore();
  }

  /**
   * Change the current game state.
   *
   * @param newState new game state.
   */
  public void changeState(State newState) {
    synchronized (lock) {
      if (gameState.equals(newState)) {
        if (gameOver) {
          stop();
          gameState = State.GAME_OVER;
        } else {
          inGameRun();
          gameState = State.GAME_RUNNING;
        }
      } else if (!(gameState == State.GAME_OVER && newState == State.PAUSE)) {
        gameState = newState;
      }
      observer.stateChanged(gameState);
    }
  }

  /**
   * Creates new empty field.
   */
  public void initializeField() {
    gameField = new GameField(CELL_CNT);
    gameField.initializeField();
  }

  private void spawnFood() {
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

  /**
   * Starts new game. Creates main objects such as snake, food and empty directions queue.
   */
  public void gameInit() {
    snake = new Snake(CELL_CNT / 2, CELL_CNT / 2);
    fruits = new ArrayList<>();
    dirsQueue = new ConcurrentLinkedQueue<>();
    changeState(State.GAME_RUNNING);
    gameOver = false;
    setSnake();
    spawnFood();
  }

  private void setSnake() {
    for (Coordinate coordinate : snake.getSnakeBody()) {
      gameField.setCellType(coordinate.getX(), coordinate.getY(),
        ObjectType.SNAKE, Tile.SNAKE_BODY);
    }
    gameField.setCellType(snake.getSnakeTailX(), snake.getSnakeTailY(),
      ObjectType.SNAKE, Tile.SNAKE_TAIL);
    gameField.setCellType(snake.getSnakeHeadX(), snake.getSnakeHeadY(),
      ObjectType.SNAKE, Tile.SNAKE_HEAD);
  }

  private void gameTick() {
    while (!Thread.interrupted()) {
      if (getState() == State.GAME_RUNNING) {
        if (dirsQueue.size() > 0) {
          snake.setDirection(dirsQueue.poll());
        }

        Coordinate dels = snake.move();

        if (snake.getSnakeHeadY() < 0 || snake.getSnakeHeadX() < 0 ||
          snake.getSnakeHeadX() >= CELL_CNT || snake.getSnakeHeadY() >= CELL_CNT) {
          changeState(State.GAME_OVER);
          gameOver = true;
          observer.tickEnd();
          return;
        }

        boolean check = false;
        for (Coordinate coordinate : snake.getSnakeBody()) {
          if (check && snake.getSnakeHeadX() == coordinate.getX() &&
            snake.getSnakeHeadY() == coordinate.getY()) {
            changeState(State.GAME_OVER);
            observer.tickEnd();
            gameOver = true;
            return;
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
          spawnFood();
        }
        gameField.makeCellEmpty(dels.getX(), dels.getY());
        setSnake();

        observer.tickEnd();
        try {
          Thread.sleep(250 - snake.getSpeed() * 10);
        } catch (InterruptedException e) {
          return;
        }
      } else {
        while (getState() != State.GAME_RUNNING) {
          synchronized (lock) {
            try {
              lock.wait();
            } catch (InterruptedException e) {
              assert false;
            }
          }
        }
      }
    }

  }

  /**
   * Starts game main tick in the dedicated thread.
   */
  public void start() {
    thread = new Thread(this::gameTick);
    thread.start();
  }

  /**
   * Stops current game by interrupting it.
   */
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

  private void inGameRun() {
    if (thread != null) {
      synchronized (lock) {
        lock.notify();
      }
    }
  }

}
