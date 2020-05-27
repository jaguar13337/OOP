package ru.nsu.fit.g18214.yakovlev;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import ru.nsu.fit.g18214.yakovlev.Model.Directions;
import ru.nsu.fit.g18214.yakovlev.Model.GameLogic;
import ru.nsu.fit.g18214.yakovlev.Model.State;

public class FieldController {
  public FieldController() {
  }

  private static final String rules = "This is the rule of Snake Game.\n" +
    "You need to score the most points.\nFor that you have to eat fruits.\n" +
    "Controls:\nW or Up -> moving up\nA or Left -> moving left\n" +
    "D or Right -> moving right\nS or Down -> moving down\n" +
    "For reading rules again, press H\nGame Restart -> R\nGame Pause -> SPACE\n" +
    "Good luck!";


  private final int timeToTick = 32000000;
  private int cellSize;
  private AnimationTimer timer;
  private GameLogic gameLogic;
  private Rectangle[][] gameField;


  @FXML
  private HBox box;

  @FXML
  private Pane paneGameField;

  @FXML
  private Label shortInfo;

  @FXML
  private Label help;

  @FXML
  private Label score;

  public void keyHandler(KeyEvent event) {
    switch (event.getCode()) {
      case R:
        startGame();
        break;
      case H:
        gameLogic.changeState(State.HELP);
        break;
      case W:
      case UP:
        gameLogic.addDir(Directions.UP);
        break;
      case S:
      case DOWN:
        gameLogic.addDir(Directions.DOWN);
        break;
      case A:
      case LEFT:
        gameLogic.addDir(Directions.LEFT);
        break;
      case D:
      case RIGHT:
        gameLogic.addDir(Directions.RIGHT);
        break;
      case SPACE:
        gameLogic.changeState(State.PAUSE);
        break;
      default:
        break;
    }
    event.consume();
  }

  private void handleGameState() {
    switch (gameLogic.getState()) {
      case HELP:
        help.setText(rules);
        shortInfo.setText("");
        break;
      case PAUSE:
        shortInfo.setText("PAUSE");
        help.setText("");
        break;
      case GAMEOVER:
        shortInfo.setText("GAME OVER");
        help.setText("");
        break;
      case DEFAULT:
        shortInfo.setText("");
        help.setText("");
        break;
    }
  }

  private void startGame() {
    gameLogic.stop();
    if (timer != null) {
      timer.stop();
    }

    gameLogic.initializeField();
    paneGameField.getChildren().clear();
    gameLogic.gameInit();

    gameField = new Rectangle[gameLogic.getCellCnt()][gameLogic.getCellCnt()];

    for (int i = 0; i < gameLogic.getCellCnt(); i++) {
      for (int j = 0; j < gameLogic.getCellCnt(); j++) {
        gameField[i][j] = new Rectangle(i * cellSize, j * cellSize, cellSize, cellSize);
        gameField[i][j].setFill(typeToPaint(gameLogic.getCellTextureType(i, j)));
        paneGameField.getChildren().add(gameField[i][j]);
      }
    }


    timer = new AnimationTimer() {
      long tick = 0;

      @Override
      public void handle(long curr) {
        if (tick == 0) {
          tick = curr;
          redrawField();
        } else if (curr - tick > timeToTick) {
          tick = curr;
          redrawField();
        }
        handleGameState();
        score.setText(gameLogic.getScore().toString());
      }
    };

    timer.start();
    gameLogic.start();

  }

  private Paint typeToPaint(TextureType type) {
    switch (type) {
      case FIELD:
        return Paint.valueOf("grey");
      case RED_FRUIT:
        return Paint.valueOf("red");
      case SNAKE_BODY:
        return Paint.valueOf("brown");
      case SNAKE_HEAD:
        return Paint.valueOf("SADDLEBROWN");
      case SNAKE_TAIL:
        return Paint.valueOf("goldenrod");
      case VIOLET_FRUIT:
        return Paint.valueOf("violet");
      case YELLOW_FRUIT:
        return Paint.valueOf("yellow");
    }
    assert false;
    return null;
  }

  private void redrawField() {
    for (int i = 0; i < gameLogic.getCellCnt(); i++) {
      for (int j = 0; j < gameLogic.getCellCnt(); j++) {
        gameField[i][j].setFill(typeToPaint(gameLogic.getCellTextureType(i, j)));
      }
    }
  }

  private void rescaleFieldSize() {
    double scale;
    if (paneGameField.getPrefWidth() / paneGameField.getPrefHeight()
      > box.getWidth() / box.getHeight()) {
      scale = box.getWidth() / paneGameField.getPrefWidth();
    } else {
      scale = box.getHeight() / paneGameField.getPrefHeight();
    }
    paneGameField.setScaleX(scale);
    paneGameField.setScaleY(scale);
    shortInfo.setScaleX(scale);
    shortInfo.setScaleY(scale);
    score.setFont(new Font("", 20 * scale));
    help.setFont(new Font("", 10 * scale));
  }


  public void initialize() {
    gameLogic = new GameLogic();
    cellSize = (int) paneGameField.getPrefWidth() / gameLogic.getCellCnt();
    ChangeListener<Number> listener = ((observable, oldValue, newValue) -> rescaleFieldSize());
    box.widthProperty().addListener(listener);
    box.heightProperty().addListener(listener);
    gameLogic.changeState(State.HELP);
    handleGameState();
  }
}
