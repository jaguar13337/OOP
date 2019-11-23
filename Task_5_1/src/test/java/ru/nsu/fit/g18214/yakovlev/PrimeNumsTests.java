package ru.nsu.fit.g18214.yakovlev;

import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;

public class PrimeNumsTests {

  @Test
  public void test2() {
    int[] test = new int[1000000];
    for (int i = 0; i<1000000; i++)
      test[i] = 1000000007;
    Assert.assertFalse(PrimeNumbers.IfThereAreOneNotPrimeConsistently(test));
  }

  @Test
  public void test2Par() {
    int[] test = new int[1000000];
    for (int i = 0; i<1000000; i++)
      test[i] = 1000000007;
    try {
      Assert.assertFalse(PrimeNumbers.ifThereAreOneNotPrimeParallel(test));
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test2parStr() {
    int[] test = new int[1000000];
    for (int i = 0; i<1000000; i++)
      test[i] = 1000000007;
    Assert.assertFalse(PrimeNumbers.ifThereAreOneNotPrimeParralelStream(test));
  }
}
