package ru.nsu.fit.g18214.yakovlev;

import java.awt.Toolkit;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class FieldController {
  private static final double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  private static final double heigth = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  private static final int cellHeightCnt = 20;
  private static final int cellWidthCnt = 40;

  static int getCellHeight() {
    return cellHeight;
  }

  static int getCellWidth() {
    return cellWidth;
  }

  private static final int cellHeight = (int) (heigth / cellHeightCnt);
  private static final int cellWidth = (int) (width / cellWidthCnt);


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

  static double getHeigth() {
    return heigth;
  }

  public void keyHandler(KeyEvent event) {
    switch (event.getCode()) {
      case R:
        GameLogic.startGame(canvas.getGraphicsContext2D());
        break;
      case H:
        GameLogic.changeHelp();
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
        GameLogic.changePause();
        break;
      default:
        break;
    }
    event.consume();
  }
}
