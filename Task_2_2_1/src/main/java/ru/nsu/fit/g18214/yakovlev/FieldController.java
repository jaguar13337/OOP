package ru.nsu.fit.g18214.yakovlev;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class FieldController {
  public FieldController() {
  }
  private static final String rules = "This is the rule of Snake Game.\n" +
    "You need to score the most points.\nFor that you have to eat fruits.\n" +
    "Controls:\nW or Up -> moving up\nA or Left -> moving left\n" +
    "D or Right -> moving right\nS or Down -> moving down\n" +
    "For reading rules again, press H\nGame Restart -> R\nGame Pause -> SPACE\n" +
    "Good luck!";

  private int CELL_SIZE;
  private AnimationTimer timer;

  @FXML
  private HBox box;

  @FXML
  private Pane gameField;

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
        GameLogic.changeState(State.HELP);
        break;
      case W:
      case UP:
        GameLogic.addDir(Directions.UP);
        break;
      case S:
      case DOWN:
        GameLogic.addDir(Directions.DOWN);
        break;
      case A:
      case LEFT:
        GameLogic.addDir(Directions.LEFT);
        break;
      case D:
      case RIGHT:
        GameLogic.addDir(Directions.RIGHT);
        break;
      case SPACE:
        GameLogic.changeState(State.PAUSE);
        break;
      default:
        break;
    }
    event.consume();
  }

  private void handleGameState() {
    switch (GameLogic.getState()) {
      case HELP:
        help.setText(rules);
        break;
      case PAUSE:
        shortInfo.setText("PAUSE");
        break;
      case GAMEOVER:
        shortInfo.setText("GAME OVER");
        break;
      case NOTHING:
        shortInfo.setText("");
        help.setText("");
        break;
    }
  }

  private void startGame() {

    if (timer != null) {
      timer.stop();
    }

    GameLogic.initializeField();
    gameField.getChildren().clear();
    for (GameObject[] gameObjects : GameLogic.getGameObjectsField()) {
      for (GameObject gameObject : gameObjects) {
        gameObject.setSize(CELL_SIZE);
        gameField.getChildren().add(gameObject);
      }
    }

    GameLogic.gameInit();

    timer = new AnimationTimer() {
      long tick = 0;

      @Override
      public void handle(long curr) {
        if (tick == 0) {
          tick = curr;
          GameLogic.gameTick();
        } else if (curr - tick > GameLogic.getTimerToTick()) {
          tick = curr;
          GameLogic.gameTick();
        }
        handleGameState();
        score.setText(GameLogic.getScore().toString());
      }
    };

    timer.start();

  }


  private void rescaleFieldSize() {
    double scale;
    if (gameField.getPrefWidth() / gameField.getPrefHeight()
      > box.getWidth() / box.getHeight()) {
      scale = box.getWidth() / gameField.getPrefWidth();
    } else {
      scale = box.getHeight() / gameField.getPrefHeight();
    }
    gameField.setScaleX(scale);
    gameField.setScaleY(scale);
    shortInfo.setScaleX(scale);
    shortInfo.setScaleY(scale);
    score.setFont(new Font("", 20 * scale));
    help.setFont(new Font("", 10 * scale));
  }


  public void initialize() {
    CELL_SIZE = (int) gameField.getPrefWidth() / GameLogic.getCellCnt();
    ChangeListener<Number> listener = ((observable, oldValue, newValue) -> rescaleFieldSize());
    box.widthProperty().addListener(listener);
    box.heightProperty().addListener(listener);
    GameLogic.changeState(State.HELP);
    handleGameState();
  }
}
