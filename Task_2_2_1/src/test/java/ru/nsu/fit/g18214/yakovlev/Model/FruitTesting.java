package ru.nsu.fit.g18214.yakovlev.Model;

import org.junit.Assert;
import org.junit.Test;

public class FruitTesting {

  @Test
  public void appleTest() {
    Fruit fruit = new Apple(0, 2);
    Assert.assertEquals(fruit.getScoreCount(), 10);
    Assert.assertEquals(fruit.getSizeChange(), 1);
    Assert.assertEquals(fruit.getSpeedChange(), 0);
    Assert.assertEquals(fruit.getX(), 0);
    Assert.assertEquals(fruit.getY(), 2);

  }

  @Test
  public void bananaTest() {
    Fruit fruit1 = new Banana(0, 2);
    Assert.assertEquals(fruit1.getScoreCount(), 5);
    Assert.assertEquals(fruit1.getSizeChange(), -1);
    Assert.assertEquals(fruit1.getSpeedChange(), -1);
    Assert.assertEquals(fruit1.getX(), 0);
    Assert.assertEquals(fruit1.getY(), 2);
  }

  @Test
  public void cranberryTest() {
    Fruit fruit2 = new Cranberry(0, 2);
    Assert.assertEquals(fruit2.getScoreCount(), 25);
    Assert.assertEquals(fruit2.getSizeChange(), 0);
    Assert.assertEquals(fruit2.getSpeedChange(), 1);
    Assert.assertEquals(fruit2.getX(), 0);
    Assert.assertEquals(fruit2.getY(), 2);
  }

}
