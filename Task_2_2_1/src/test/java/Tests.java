import java.security.PublicKey;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.Model.GameLogic;
import ru.nsu.fit.g18214.yakovlev.Model.State;
import ru.nsu.fit.g18214.yakovlev.Model.TypeForTextures;

public class Tests {
  @Test
  public void fieldInitializeTest() {
    GameLogic logic = new GameLogic();

    logic.initializeField();

    for (int i = 0; i < logic.getCellCnt(); i++) {
      for (int j = 0; j < logic.getCellCnt(); j++) {
        Assert.assertEquals(logic.getCellTypeForTextures(i,j), TypeForTextures.FIELD);
      }
    }
  }

  @Test
  public void gameInitTest() {
    GameLogic logic = new GameLogic();

    logic.initializeField();
    logic.gameInit();

    Assert.assertEquals((int)logic.getScore(), 0);
    Assert.assertEquals(logic.getState(), State.NOTHING);
  }

  @Test
  public void stateCheck() {
    GameLogic logic = new GameLogic();

    logic.initializeField();
    logic.gameInit();

    Assert.assertEquals(logic.getState(), State.NOTHING);

    logic.changeState(State.HELP);
    Assert.assertEquals(logic.getState(), State.HELP);

    logic.changeState(State.GAMEOVER);
    Assert.assertEquals(logic.getState(), State.GAMEOVER);


    logic.changeState(State.PAUSE);
    Assert.assertEquals(logic.getState(), State.PAUSE);
  }
}
