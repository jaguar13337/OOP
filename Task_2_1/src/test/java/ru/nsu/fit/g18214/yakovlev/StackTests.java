package ru.nsu.fit.g18214.yakovlev;

import org.junit.Assert;
import org.junit.Test;

public class StackTests {
    @Test
    public void test1() {
        Stack stack = new Stack(3);
        stack.push(1);
        stack.push("jiga jiga");
        stack.push(1000000000000000000L);
        stack.push('a');
        stack.push(10.5);
        Assert.assertEquals(5, stack.count());
        Assert.assertEquals(10.5, stack.pop());
        Assert.assertEquals('a', stack.pop());
        Assert.assertEquals(1000000000000000000L, stack.pop());
        Assert.assertEquals("jiga jiga", stack.pop());
        Assert.assertEquals(1, stack.pop());
        boolean exceptionRised = false;
        try {
            stack.pop();
        }
        catch(StackException e) {
            exceptionRised = true;
        }
        Assert.assertEquals(true, exceptionRised);
        stack = new Stack();
        stack.push(1400);
        stack.push(1500);
        stack.push(1600);
        stack.push(1700);
        stack.push(1800);
        int j = 0;
        for (Object i: stack) {
            Assert.assertEquals(1800-j, i);
            j+=100;
        }
    }
}
