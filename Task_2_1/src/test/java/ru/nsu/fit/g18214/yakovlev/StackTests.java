package ru.nsu.fit.g18214.yakovlev;

import org.junit.Assert;
import org.junit.Test;

public class StackTests {
  @Test
  public void test1() {
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    Assert.assertEquals(5, stack.count());
    Assert.assertEquals((Integer) 5, stack.pop());
    Assert.assertEquals((Integer) 4, stack.pop());
    Assert.assertEquals((Integer) 3, stack.pop());
    Assert.assertEquals((Integer) 2, stack.pop());
    Assert.assertEquals((Integer) 1, stack.pop());
    boolean exceptionRise = false;
    try {
      stack.pop();
    } catch (StackException e) {
      exceptionRise = true;
    }
    Assert.assertEquals(true, exceptionRise);
    stack = new Stack<Integer>();
    stack.push(1400);
    stack.push(1500);
    stack.push(1600);
    stack.push(1700);
    stack.push(1800);
    int j = 0;
    for (Integer i : stack) {
      Assert.assertEquals((Integer) (1800 - j), i);
      j += 100;
    }
    Assert.assertEquals((Integer) 1800, stack.pop());
  }

  @Test
  public void test2() {
    Stack<String> stack = new Stack<String>();
    stack.push("kek");
    stack.push("lol");
    stack.push("arbidol");
    Assert.assertEquals(3, stack.count());
    Assert.assertEquals("arbidol", stack.pop());
    Assert.assertEquals("lol", stack.pop());
    Assert.assertEquals("kek", stack.pop());
  }
}
