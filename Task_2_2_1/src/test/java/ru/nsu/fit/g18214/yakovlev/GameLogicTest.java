package ru.nsu.fit.g18214.yakovlev;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.Model.GameLogic;
import ru.nsu.fit.g18214.yakovlev.Model.State;

public class GameLogicTest {
  @Test
  public void fieldInitializeTest() {
    FieldController controller = new FieldController();
    controller.initialize();
    GameLogic logic = controller.getGameLogic();

    logic.initializeField();

    for (int i = 0; i < logic.getCellCnt(); i++) {
      for (int j = 0; j < logic.getCellCnt(); j++) {
        Assert.assertEquals(logic.getCellTextureType(i, j), Tile.FIELD);
      }
    }
  }

  @Test
  public void gameInitTest() {
    FieldController controller = new FieldController();
    controller.initialize();
    GameLogic logic = controller.getGameLogic();

    logic.initializeField();
    logic.gameInit();

    Assert.assertEquals((int) logic.getScore(), 0);
    Assert.assertEquals(logic.getState(), State.GAMERUNNIG);
  }

  @Test
  public void stateCheck() {

    FieldController controller = new FieldController();
    controller.initialize();

    GameLogic logic = controller.getGameLogic();

    logic.initializeField();
    logic.gameInit();

    Assert.assertEquals(logic.getState(), State.GAMERUNNIG);

    logic.changeState(State.HELP);
    Assert.assertEquals(logic.getState(), State.HELP);

    logic.changeState(State.GAMEOVER);
    Assert.assertEquals(logic.getState(), State.GAMEOVER);


    logic.changeState(State.PAUSE);
    Assert.assertEquals(logic.getState(), State.PAUSE);
  }
}
