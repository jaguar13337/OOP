package ru.nsu.fit.g18214.yakovlev.Model;

import org.junit.Assert;
import org.junit.Test;

public class ModelTesting {

  @Test
  public void fruitTest() {
    Fruit fruit = new Apple(0,2);
    Assert.assertEquals(fruit.getScoreCount(), 10);
    Assert.assertEquals(fruit.getSizeChange(), 1);
    Assert.assertEquals(fruit.getSpeedChange(), 0);
    Assert.assertEquals(fruit.getX(), 0);
    Assert.assertEquals(fruit.getY(), 2);



    Fruit fruit1 = new Banana(0,2);
    Assert.assertEquals(fruit1.getScoreCount(), 5);
    Assert.assertEquals(fruit1.getSizeChange(), -1);
    Assert.assertEquals(fruit1.getSpeedChange(), -1);
    Assert.assertEquals(fruit1.getX(), 0);
    Assert.assertEquals(fruit1.getY(), 2);



    Fruit fruit2 = new Cranberry(0,2);
    Assert.assertEquals(fruit2.getScoreCount(), 25);
    Assert.assertEquals(fruit2.getSizeChange(), 0);
    Assert.assertEquals(fruit2.getSpeedChange(), 1);
    Assert.assertEquals(fruit2.getX(), 0);
    Assert.assertEquals(fruit2.getY(), 2);

  }

  @Test
  public void snakeTest() {
    Snake snake = new Snake(0,6);

    Assert.assertEquals(snake.getSnakeHeadX(), 0);
    Assert.assertEquals(snake.getSnakeHeadY(), 6);

    snake.eatFruit(new Apple(0,0));

    Assert.assertEquals(snake.getScore(), 10);
    Assert.assertEquals(snake.getSnakeBody().size(), 2);

    snake.eatFruit(new Cranberry(0,0));

    Assert.assertEquals(snake.getScore(), 35);
    Assert.assertEquals(snake.getSpeed(), 6);

    snake.eatFruit(new Banana(0,0));

    Assert.assertEquals(snake.getScore(), 40);
    Assert.assertEquals(snake.getSpeed(), 5);
    Assert.assertEquals(snake.getSnakeBody().size(), 1);

  }

}
