package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

class GameLogic {

  private static final String rules = "This is the game rules of Snake Game.\n" +
    "You need to score the most points. For that you have to eat apples.\n" +
    "Controls: W or Up Arrow -> moving up\n A or Left Arrow -> moving left\n" +
    "D or Right Arrow -> moving right\n S or Down Arrow -> moving down\n" +
    "For reading rules again, press H\n Game Restart -> R\n Game Pause -> SPACE\n";

  private static AnimationTimer timer;

  private static Random random = new Random();
  private static int speed;
  private static final int fruitCnt = 1;
  private static Snake snake;
  private static Directions direction = Directions.LEFT;
  private static Directions prevDir;
  private static int cellCntWidth = FieldController.getCellWidthCnt();
  private static int cellCntHeight = FieldController.getCellHeightCnt();
  private static List<Directions> dirsQueue;

  private static final double fontSize = FieldController.getHeigth()*FieldController.getWidth()/69120;

  private static Fruit fruit;
  private static State gameState = State.Nothing;

  static void setDirection(Directions dir) {
    dirsQueue.add(dir);
  }

  static void changeState(State newState) {
    if (gameState.equals(newState)) {
      gameState = State.Nothing;
    } else {
      gameState = newState;
    }
  }
  private static void newFood() {
    boolean flag = true;
    while (flag) {
      flag = false;
      fruit = new Apple(random.nextInt(cellCntWidth), random.nextInt(cellCntHeight));

      for (Rectangle body : snake.getSnakeBody()) {
        if (body.getX() == fruit.getX() && body.getY() == fruit.getY()) {
          flag = true;
          break;
        }
      }
    }
    speed++;
  }

  private static void showScore(GraphicsContext gc) {
    gc.setFill(Color.RED);
    gc.setFont(new Font("", fontSize));
    gc.fillText("SCORE: " + (speed-6), 10, 30);
  }

  static private void putPause(GraphicsContext gc) {
    clearField(gc);

    gc.setFill(Color.RED);
    gc.setFont(new Font("", fontSize));
    gc.fillText("PAUSE", FieldController.getWidth() / 2, FieldController.getHeigth() / 2);
  }

  static private void putHelp(GraphicsContext gc) {
    clearField(gc);

    gc.setFill(Color.RED);
    gc.setFont(new Font("", fontSize));
    gc.fillText(rules, FieldController.getWidth() / 2, FieldController.getHeigth() / 2);
  }

  static private void clearField(GraphicsContext gc) {
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, FieldController.getWidth(), FieldController.getHeigth());
  }

  static void startGame(GraphicsContext gc) {
    if (timer != null) {
      timer.stop();
    }

    dirsQueue = new ArrayList<>();

    snake = new Snake(cellCntWidth / 2, cellCntHeight / 2);
    snake.addBody(cellCntWidth / 2, cellCntHeight / 2);
    snake.addBody(cellCntWidth / 2, cellCntHeight / 2);

    speed = 5;
    newFood();
    //TODO load from config
    gameState = State.Nothing;

    timer = new AnimationTimer() {
      long tick = 0;
      @Override
      public void handle(long curr) {
        if (tick == 0) {
          tick = curr;
          tick(gc);
          return;
        }
        if (curr - tick > 1000000000 / speed) {
          tick = curr;
          tick(gc);
        }
      }
    };

    timer.start();

  }

  private static void tick(GraphicsContext gc) {
    if (gameState == State.HELP) {
      putHelp(gc);
    } else if (gameState == State.PAUSE) {
      putPause(gc);
    } else if (gameState == State.Nothing) {
      if (dirsQueue.size() > 0) {
        prevDir = direction;
        direction = dirsQueue.remove(0);
      }
      switch (direction) {
        case UP:
          if (prevDir != Directions.DOWN) {
            snake.addBody(snake.getSnakeHeadX(), snake.getSnakeHeadY() - 1);
          } else {
            snake.addBody(snake.getSnakeHeadX(), snake.getSnakeHeadY() + 1);
            direction = Directions.DOWN;
          }
          break;
        case DOWN:
          if (prevDir != Directions.UP) {
            snake.addBody(snake.getSnakeHeadX(), snake.getSnakeHeadY() + 1);
          } else {
            snake.addBody(snake.getSnakeHeadX(), snake.getSnakeHeadY() - 1);
            direction = Directions.UP;
          }
          break;
        case LEFT:
          if (prevDir != Directions.RIGHT) {
            snake.addBody(snake.getSnakeHeadX() - 1, snake.getSnakeHeadY());
          } else {
            snake.addBody(snake.getSnakeHeadX() + 1, snake.getSnakeHeadY());
            direction = Directions.RIGHT;
          }
          break;
        case RIGHT:
          if (prevDir != Directions.LEFT) {
            snake.addBody(snake.getSnakeHeadX() + 1, snake.getSnakeHeadY());
          } else {
            snake.addBody(snake.getSnakeHeadX() - 1, snake.getSnakeHeadY());
            direction = Directions.LEFT;
          }
          break;
      }
      snake.getSnakeBody().remove(snake.getSnakeBody().size() - 1);
      if (snake.getSnakeHeadY() < 0 || snake.getSnakeHeadX() < 0 ||
        snake.getSnakeHeadX() > cellCntWidth || snake.getSnakeHeadY() > cellCntHeight) {
        gameState = State.GAMEOVER;
      }

      if (fruit.getX() == snake.getSnakeHeadX()
        && fruit.getY() == snake.getSnakeHeadY()) {

        snake.addBody(-1, -1);
        newFood();
      }

      for (int i = 1; i < snake.getSnakeBody().size(); i++) {
        if (snake.getSnakeHeadX() == snake.getSnakeBodyX(i) &&
          snake.getSnakeHeadY() == snake.getSnakeBodyY(i)) {
          gameState = State.GAMEOVER;
        }
      }
      //FILL
      clearField(gc);

      showScore(gc);

      gc.setFill(fruit.getColor());
      gc.fillRect(fruit.getX() * FieldController.getCellWidth(),
        fruit.getY() * FieldController.getCellHeight(),
        FieldController.getCellWidth(), FieldController.getCellHeight());



      for (Rectangle r : snake.getSnakeBody()) {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(r.getX() * FieldController.getCellWidth(),
          r.getY() * FieldController.getCellHeight(),
          FieldController.getCellWidth() - 1, FieldController.getCellHeight() - 1);
      }


    } else if (gameState == State.GAMEOVER){
      gc.setFill(Color.RED);
      gc.setFont(new Font("", fontSize));
      gc.fillText("GAME OVER", FieldController.getWidth() / 2, FieldController.getHeigth() / 2);
    }


  }

}
