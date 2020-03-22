package ru.nsu.fit.g18214.yakovlev;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Cell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FieldController {
  private static final double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  private static final double heigth = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  private final int sqrtCellCount = 15;
  private final int fruitCnt = 1;
  private final int cellHeight = (int)(heigth/sqrtCellCount);
  private final int cellWidth = (int)(width/sqrtCellCount);

  public FieldController() {}

  private static final String rules = "This is the game rules of Snake Game.\n" +
    "You need to score the most points. For that you have to eat apples.\n" +
    "Controls: W or Up Arrow -> moving up\n A or Left Arrow -> moving left\n" +
    "D or Right Arrow -> moving right\n S or Down Arrow -> moving down\n" +
    "For reading rules again, press H\n Game Restart -> R\n Game Pause -> SPACE\n";

  @FXML AnchorPane pane;
  @FXML Text score;
  @FXML Text gameRules;

  private Rectangle[][] field = new Rectangle[sqrtCellCount][sqrtCellCount];

  static double getWidth() {
    System.out.println(width);
    return width;
  }

  static double getHeigth() {
    System.out.println(heigth);
    return heigth;
  }

  public void keyHandler(KeyEvent event) {
    switch (event.getCode()) {
      case R:
        startGame();
        gameRules.setText("");
        break;
      case H:
        gameRules.setText(rules);
        break;
      default:
        break;
    }
    event.consume();
  }


  private void startGame() {
    Snake snake = new Snake(sqrtCellCount/2, sqrtCellCount/2);
    pane.setStyle("-fx-background-color: white");
    for (int i = 0; i<sqrtCellCount; i++) {
      for (int j = 0; j<sqrtCellCount; j++) {
        field[i][j] = new Rectangle(i*cellWidth, j*cellHeight,cellWidth, cellHeight);
        if (i == snake.getCurrX() && j == snake.getCurrY()) {
          field[i][j].setFill(Paint.valueOf("blue"));
        } else {
          field[i][j].setFill(Paint.valueOf("black"));
        }
        pane.getChildren().add(field[i][j]);
      }
    }
  }

  @FXML
  public void initialize() {
    score.setText("Press R to start the game.");
    gameRules.setText(rules);
  }
}
