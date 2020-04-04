package ru.nsu.fit.g18214.yakovlev;

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


  private static Random random = new Random();
  private static int speed;
  private static final int fruitCnt = 1;
  private static Snake snake;
  private static Directions direction = Directions.LEFT;
  private static Directions prevDir;
  private static int cellCntWidth = FieldController.getCellWidthCnt();
  private static int cellCntHeight = FieldController.getCellHeightCnt();

  private static int foodX;
  private static int foodY;
  private static Fruit fruit;

  private static boolean gameOver;
  private static boolean pause;
  private static boolean help;

  static void setDirection(Directions dir) {
    prevDir = direction;
    direction = dir;
  }

  static void changePause() {
    pause = !pause;
  }
  static void changeHelp() {
    help = !help;
  }
  private static void newFood() {
    boolean flag = true;
    while (flag) {
      flag = false;
      foodX = random.nextInt(cellCntWidth);
      foodY = random.nextInt(cellCntHeight);
      fruit = new Apple(foodX, foodY);

      for (Rectangle body : snake.getSnakeBody()) {
        if (body.getX() == fruit.getX() && body.getY() == fruit.getY()) {
          flag = true;
        }
      }
    }
    speed++;
  }

  static void startGame(GraphicsContext gc) {
    snake = new Snake(cellCntWidth / 2, cellCntHeight / 2);
    snake.addBody(cellCntWidth / 2, cellCntHeight / 2);
    snake.addBody(cellCntWidth / 2, cellCntHeight / 2);

    speed = 2;
    newFood();
    //TODO load from config
    gameOver = false;
    help = false;
    pause = false;

    new AnimationTimer() {
      long tick = 0;

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
    }.start();
  }

  static private void putPause(GraphicsContext gc) {
    gc.setFill(Color.RED);
    gc.setFont(new Font("", 50));
    gc.fillText("PAUSE", FieldController.getWidth()/2, FieldController.getHeigth()/2);
  }

  static private void putHelp(GraphicsContext gc) {
    gc.setFill(Color.RED);
    gc.setFont(new Font("", 30));
    gc.fillText(rules, FieldController.getWidth()/2, FieldController.getHeigth()/2);
  }

  private static void tick(GraphicsContext gc) {
    if (help) {
      putHelp(gc);
      return;
    }
    if (pause) {
      putPause(gc);
      return;
    }

    if (!gameOver) {
      boolean check = true;
      while (check) {
        check = false;
        switch (direction) {
          case UP:
            if (prevDir != Directions.DOWN) {
              snake.addBody(snake.getSnakeHeadX(), snake.getSnakeHeadY() - 1);
            } else {
              direction = prevDir;
              check = true;
            }
            break;
          case DOWN:
            if (prevDir != Directions.UP) {
              snake.addBody(snake.getSnakeHeadX(), snake.getSnakeHeadY() + 1);
            } else {
              direction = prevDir;
              check = true;
            }
            break;
          case LEFT:
            if (prevDir != Directions.RIGHT) {
              snake.addBody(snake.getSnakeHeadX() - 1, snake.getSnakeHeadY());
            } else {
              direction = prevDir;
              check = true;
            }
            break;
          case RIGHT:
            if (prevDir != Directions.LEFT) {
              snake.addBody(snake.getSnakeHeadX() + 1, snake.getSnakeHeadY());
            } else {
              direction = prevDir;
              check = true;
            }
            break;
        }
      }
      snake.getSnakeBody().remove(snake.getSnakeBody().size() - 1);
      if (snake.getSnakeHeadY() < 0 || snake.getSnakeHeadX() < 0 ||
        snake.getSnakeHeadX() > cellCntWidth || snake.getSnakeHeadY() > cellCntHeight) {
        gameOver = true;
      }

      if (foodX == snake.getSnakeHeadX()
        && foodY == snake.getSnakeHeadY()) {
        snake.addBody(-1, -1);
        newFood();
      }

      for (int i = 1; i < snake.getSnakeBody().size(); i++) {
        if (snake.getSnakeHeadX() == snake.getSnakeBodyX(i) &&
          snake.getSnakeHeadY() == snake.getSnakeBodyY(i)) {
          gameOver = true;
        }
      }

      //FILL
      gc.setFill(Color.BLACK);
      gc.fillRect(0, 0, FieldController.getWidth(), FieldController.getHeigth());

      gc.setFill(fruit.getColor());
      gc.fillRect(foodX * FieldController.getCellWidth(),
        foodY * FieldController.getCellHeight(),
        FieldController.getCellWidth(), FieldController.getCellHeight());


      for (Rectangle r : snake.getSnakeBody()) {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(r.getX() * FieldController.getCellWidth(),
          r.getY() * FieldController.getCellHeight(),
          FieldController.getCellWidth()-1, FieldController.getCellHeight()-1);
      }

    } else {
      gc.setFill(Color.RED);
      gc.setFont(new Font("", 50  ));
      gc.fillText("GAME OVER", FieldController.getWidth()/2, FieldController.getHeigth()/2);
    }


  }

}
