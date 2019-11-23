package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PrimeNumbers{
  private static boolean notPrime(int number)  {
    if (number % 2 == 0)
      return true;
    for (int i = 3; i*i<=number; i+=2) {
      if (number % i == 0) {
        return true;
      }
    }
    return false;
  }

  public static boolean IfThereAreOneNotPrimeConsistently(int[] numbers) {
    for (int number : numbers) {
      if (notPrime(number))
        return true;
    }
    return false;
  }

  public static boolean ifThereAreOneNotPrimeParallel(int[] numbers) throws InterruptedException, ExecutionException {
    ExecutorService es = Executors.newFixedThreadPool(8);
    List<Callable<Boolean>> tasks = new ArrayList<>();
    class PrimeTask implements Callable<Boolean> {
      private int num;
      private PrimeTask(int num){
        this.num = num;
      }
      @Override
      public Boolean call() {
        return notPrime(num);
      }
    }
    for (int number : numbers) {
      tasks.add(new PrimeTask(number));
    }
    List <Future<Boolean>> listResult = es.invokeAll(tasks);
    es.shutdown();
    if (!es.awaitTermination(10, TimeUnit.SECONDS))
      es.shutdownNow();
    for (Future<Boolean> f : listResult) {
      if (f.get())
        return true;
    }
    return false;
  }

  public static boolean ifThereAreOneNotPrimeParralelStream(int [] numbers) {
    List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
    return list.parallelStream().anyMatch(PrimeNumbers::notPrime);
  }

}
