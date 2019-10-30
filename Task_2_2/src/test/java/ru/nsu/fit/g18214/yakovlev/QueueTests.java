package ru.nsu.fit.g18214.yakovlev;

import org.junit.Assert;
import org.junit.Test;

public class QueueTests {
  @Test
  public void test1() {
    OrderedQueue<Integer, String> queue = new OrderedQueue<>();
    queue.insert(10, "собака");
    queue.insert(200, "человек");
    Assert.assertEquals("человек", queue.extractMax());
    queue.insert(5, "птица");
    Assert.assertEquals("собака", queue.extractMax());
    Assert.assertEquals("птица", queue.extractMax());
    Assert.assertNull(queue.extractMax());
  }

  @Test
  public void test2() {
    OrderedQueue<String, Integer> queue = new OrderedQueue<>();
    queue.insert("собака", 10);
    queue.insert("человек", 200);
    Assert.assertEquals((Integer) 200, queue.extractMax());
    queue.insert("птица", 5);
    Assert.assertEquals((Integer) 10, queue.extractMax());
    Assert.assertEquals((Integer) 5, queue.extractMax());
    Assert.assertNull(queue.extractMax());
  }

  @Test
  public void test3() {
    OrderedQueue<String, Integer> queue = new OrderedQueue<>();
    queue.insert("собака", 200);
    queue.insert("человек", 300);
    queue.insert("птица", 100);
    int check = 0;
    for (Integer val: queue) {
        Assert.assertEquals((Integer)(300-check), val);
        check += 100;
    }
  }
}
