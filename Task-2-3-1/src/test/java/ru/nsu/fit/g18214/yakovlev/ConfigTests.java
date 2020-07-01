package ru.nsu.fit.g18214.yakovlev;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Config;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Engine;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Checkpoint;
import ru.nsu.fit.g18214.yakovlev.dsl.engine.Model.Group;

public class ConfigTests {
  @Test
  public void groupFilling() {
    try {
      Config config = (Config) Engine.executeDSL(Objects.requireNonNull(getClass()
        .getClassLoader()
        .getResource("config.nsu"))
        .getPath());
      Assert.assertEquals(1, config.getGroups().size());
      Group[] groups = new Group[config.getGroups().size()];
      config.getGroups().toArray(groups);
      Assert.assertEquals((Integer) 18214, groups[0].getGroupId());
      Assert.assertEquals(1, groups[0].getStudents().size());
      Assert.assertEquals("ayakovlev", groups[0].getStudents().get(0).getId());
    } catch (FileNotFoundException ignored) {
      Assert.fail();
    }
  }

  @Test
  public void controlsFilling() {
    try {
      Config config = (Config) Engine.executeDSL(Objects.requireNonNull(getClass()
        .getClassLoader()
        .getResource("config.nsu"))
        .getPath());
      Assert.assertEquals(1, config.getControls().size());
      Checkpoint[] checkpoints = new Checkpoint[config.getControls().size()];
      config.getControls().toArray(checkpoints);
      Assert.assertEquals(new SimpleDateFormat("dd.MM.yyyy").parse("01.05.2020"),
        checkpoints[0].getDate());
      Assert.assertEquals((Integer) 6, checkpoints[0].getExc());
      Assert.assertEquals((Integer) 4, checkpoints[0].getGood());
      Assert.assertEquals((Integer) 2, checkpoints[0].getSat());
    } catch (FileNotFoundException | ParseException ignored) {
      Assert.fail();
    }
  }

  @Test
  public void lessonFilling() {
    try {
      Config config = (Config) Engine.executeDSL(Objects.requireNonNull(getClass()
        .getClassLoader()
        .getResource("config.nsu"))
        .getPath());
      Group[] group = new Group[config.getGroups().size()];
      config.getGroups().toArray(group);
      Assert.assertEquals(5, group[0].getLessons().size()); // 4 from 04.02 to 31.02 every 7 days + one additional at 07.02
    } catch (FileNotFoundException ignored) {
      Assert.fail();
    }
  }

}
