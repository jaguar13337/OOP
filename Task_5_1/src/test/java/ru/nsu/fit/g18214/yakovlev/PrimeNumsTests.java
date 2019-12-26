package ru.nsu.fit.g18214.yakovlev;

import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;

public class PrimeNumsTests {

  @Test
  public void putNull() {
    int[] test = null;
    try{
      PrimeNumbers.ifThereAreOneNotPrimeConsistently(test);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
    try{
      PrimeNumbers.ifThereAreOneNotPrimeParallel(test);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    try{
      PrimeNumbers.ifThereAreOneNotPrimeParralelStream(test);
      Assert.fail();
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void test2() {
    int[] test = new int[1000000];
    for (int i = 0; i<1000000; i++)
      test[i] = 1000000007;
    Assert.assertFalse(PrimeNumbers.ifThereAreOneNotPrimeConsistently(test));
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
