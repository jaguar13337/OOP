package ru.nsu.fit.g18214.yakovlev;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class FieldController {
  private static double width = 640;
  private static double height = 480;

  static void setWidth(double width) {
    FieldController.width = width;
    recountCellWidth();
    GameLogic.recountFontSize();
  }

  static void setHeight(double height) {
    FieldController.height = height;
    recountCellHeight();
    GameLogic.recountFontSize();
  }

  private static final int cellHeightCnt = 20;
  private static final int cellWidthCnt = 40;

  static int getCellHeight() {
    return cellHeight;
  }
  static int getCellWidth() {
    return cellWidth;
  }

  private static int cellHeight = (int) (height / cellHeightCnt);
  private static int cellWidth = (int) (width / cellWidthCnt);

  private static void recountCellWidth() {
    cellWidth = (int)(width/cellWidthCnt);
  }

  private static void recountCellHeight() {
    cellHeight = (int)(height /cellHeight);
  }
  public FieldController() {
  }
  @FXML
  VBox box;
  @FXML
  Canvas canvas;

  static int getCellWidthCnt() {
    return cellWidthCnt;
  }

  static int getCellHeightCnt() {
    return cellHeightCnt;
  }

  static double getWidth() {
    return width;
  }

  static double getHeight() {
    return height;
  }

  public void keyHandler(KeyEvent event) {
    switch (event.getCode()) {
      case R:
        GameLogic.startGame(canvas.getGraphicsContext2D());
        break;
      case H:
        GameLogic.changeState(State.HELP);
        break;
      case W:
      case UP:
        GameLogic.setDirection(Directions.UP);
        break;
      case S:
      case DOWN:
        GameLogic.setDirection(Directions.DOWN);
        break;
      case A:
      case LEFT:
        GameLogic.setDirection(Directions.LEFT);
        break;
      case D:
      case RIGHT:
        GameLogic.setDirection(Directions.RIGHT);
        break;
      case SPACE:
        GameLogic.changeState(State.PAUSE);
        break;
      default:
        break;
    }
    event.consume();
  }

  public void initialize() {
    GameLogic.startMenu(canvas.getGraphicsContext2D());
  }
}
