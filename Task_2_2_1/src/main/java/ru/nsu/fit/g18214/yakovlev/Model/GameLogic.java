package ru.nsu.fit.g18214.yakovlev.Model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import ru.nsu.fit.g18214.yakovlev.FieldController;
import ru.nsu.fit.g18214.yakovlev.TextureType;


/**
 * This is the main Model class, which is responsible for the whole game state and its logic.
 */
public class GameLogic {

  private final int CELL_CNT = 25;
  private final int FRUIT_COUNT = 4;
  private Random random = new Random();
  private Snake snake;
  private ConcurrentLinkedQueue<Fruit> fruits;
  private State gameState = State.DEFAULT;
  private boolean gameOver;
  private Deque<Directions> dirsQueue;
  private GameField gameField;
  private Thread thread;
  private FieldController controller;

  public GameLogic(FieldController controller) {
    this.controller = controller;
  }

  /**
   * Returns type of gameField cell with given coordinates,
   * which should be used for coloring this cell in the controller.
   *
   * @param x first coordinate
   * @param y second coordinate
   * @return Type, which is used for coloring it in the controller.
   */
  public TextureType getCellTextureType(int x, int y) {
    return gameField.getTypeForTextures(x, y);
  }

  /**
   * Added direction after the users input to the directions queue.
   *
   * @param dir given direction.
   */
  public void addDir(Directions dir) {
    if (dirsQueue.size() == 0 || dirsQueue.peekLast() != dir) {
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
    return gameState;
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
    if (gameState.equals(newState)) {
      if (gameOver) {
        stop();
        gameState = State.GAMEOVER;
      } else {
        start();
        gameState = State.DEFAULT;
      }
    } else {
      stop();
      gameState = newState;
    }
    System.out.println(gameState);
    controller.handleGameState();

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
    fruits = new ConcurrentLinkedQueue<>();
    dirsQueue = new ArrayDeque<>();
    changeState(State.DEFAULT);
    gameOver = false;
    setSnake();
    spawnFood();
  }

  private void setSnake() {
    for (Coordinate coordinate : snake.getSnakeBody()) {
      gameField.setCellType(coordinate.getX(), coordinate.getY(),
        ObjectType.SNAKE, TextureType.SNAKE_BODY);
    }
    gameField.setCellType(snake.getSnakeTailX(), snake.getSnakeTailY(),
      ObjectType.SNAKE, TextureType.SNAKE_TAIL);
    gameField.setCellType(snake.getSnakeHeadX(), snake.getSnakeHeadY(),
      ObjectType.SNAKE, TextureType.SNAKE_HEAD);
  }

  private void gameTick() {
    while (!Thread.interrupted()) {
      if (gameState == State.DEFAULT) {
        if (dirsQueue.size() > 0) {
          snake.setDirection(dirsQueue.removeFirst());
        }

        Coordinate dels = snake.move();

        if (snake.getSnakeHeadY() < 0 || snake.getSnakeHeadX() < 0 ||
          snake.getSnakeHeadX() >= CELL_CNT || snake.getSnakeHeadY() >= CELL_CNT) {
          changeState(State.GAMEOVER);
          gameOver = true;
          controller.redrawField();
          return;
        }

        boolean check = false;
        for (Coordinate coordinate : snake.getSnakeBody()) {
          if (check && snake.getSnakeHeadX() == coordinate.getX() &&
            snake.getSnakeHeadY() == coordinate.getY()) {
            changeState(State.GAMEOVER);
            controller.redrawField();
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

        controller.redrawField();
        try {
          Thread.sleep(250 - snake.getSpeed() * 10);
        } catch (InterruptedException e) {
          return;
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

}
