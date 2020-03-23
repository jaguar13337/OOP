package ru.nsu.fit.g18214.yakovlev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.pizzeria.PizzeriaConfig;

public class ConfigTests {
  @Test
  public void configTest() throws FileNotFoundException {
    try {
      PizzeriaConfig config = PizzeriaConfig.makeConfig(new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("test.json")).getFile()));

      Assert.assertEquals(5000, config.getWorkingTime());
      Assert.assertEquals(1, config.getStorageCapacity());
      Assert.assertEquals(50, config.getQueueCapacity());
      Assert.assertEquals(350, config.getGeneratorSpeed());

      Assert.assertEquals(2, config.getBakers().size());
      Assert.assertEquals((Integer) 520, config.getBakers().get(0));
      Assert.assertEquals((Integer) 500, config.getBakers().get(1));

      Assert.assertEquals(2, config.getCouriers().size());
      Assert.assertEquals(2000, config.getCouriers().get(0).getDeliveryTime());
      Assert.assertEquals(1, config.getCouriers().get(0).getLuggageCapacity());
      Assert.assertEquals(2000, config.getCouriers().get(1).getDeliveryTime());
      Assert.assertEquals(1, config.getCouriers().get(1).getLuggageCapacity());
    } catch (FileNotFoundException e) {
      Assert.fail();
    }

    try {
      PizzeriaConfig config = PizzeriaConfig.makeConfig(new FileReader(getClass().getClassLoader().getResource("emptyTest.json").getFile()));
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }

  }
}
