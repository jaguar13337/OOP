package ru.nsu.fit.g18214.yakovlev.Model;

import org.junit.Assert;
import org.junit.Test;

public class SnakeTesting {

  @Test
  public void snakeCreatingTest() {
    Snake snake = new Snake(0, 6);

    Assert.assertEquals(snake.getSnakeHeadX(), 0);
    Assert.assertEquals(snake.getSnakeHeadY(), 6);
  }

  @Test
  public void snakeMovingUp() {
    Snake snake = new Snake(1, 1);
    snake.setDirection(Directions.LEFT);
    snake.setDirection(Directions.UP);

    Coordinate coordinates = snake.move();
    Assert.assertEquals(1, coordinates.getX());
    Assert.assertEquals(1, coordinates.getY());

    Assert.assertEquals(1, snake.getSnakeHeadX());
    Assert.assertEquals(0, snake.getSnakeHeadY());
  }

  @Test
  public void snakeMovingDown() {
    Snake snake = new Snake(0, 2);
    snake.setDirection(Directions.LEFT);
    snake.setDirection(Directions.DOWN);

    Coordinate coordinates = snake.move();
    Assert.assertEquals(0, coordinates.getX());
    Assert.assertEquals(2, coordinates.getY());

    Assert.assertEquals(0, snake.getSnakeHeadX());
    Assert.assertEquals(3, snake.getSnakeHeadY());
  }

  @Test
  public void snakeMovingLeft() {
    Snake snake = new Snake(2, 2);
    snake.setDirection(Directions.LEFT);

    Coordinate coordinates = snake.move();
    Assert.assertEquals(2, coordinates.getX());
    Assert.assertEquals(2, coordinates.getY());

    Assert.assertEquals(1, snake.getSnakeHeadX());
    Assert.assertEquals(2, snake.getSnakeHeadY());
  }

  @Test
  public void snakeMovingRight() {
    Snake snake = new Snake(3, 3);
    snake.setDirection(Directions.RIGHT);

    Coordinate coordinates = snake.move();
    Assert.assertEquals(3, coordinates.getX());
    Assert.assertEquals(3, coordinates.getY());

    Assert.assertEquals(4, snake.getSnakeHeadX());
    Assert.assertEquals(3, snake.getSnakeHeadY());
  }

  @Test
  public void snakeEatingTest() {
    Snake snake = new Snake(0, 6);

    Assert.assertEquals(0, snake.getSnakeHeadX());
    Assert.assertEquals(6, snake.getSnakeHeadY());
    snake.eatFruit(new Apple(0, 0));

    Assert.assertEquals(10, snake.getScore());
    Assert.assertEquals(2, snake.getSnakeBody().size());

    snake.eatFruit(new Cranberry(0, 0));

    Assert.assertEquals(35, snake.getScore());
    Assert.assertEquals(6, snake.getSpeed());

    snake.eatFruit(new Banana(0, 0));

    Assert.assertEquals(40, snake.getScore());
    Assert.assertEquals(5, snake.getSpeed());
    Assert.assertEquals(1, snake.getSnakeBody().size());

  }
}
