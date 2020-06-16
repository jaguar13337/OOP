package ru.nsu.fit.g18214.yakovlev.git;

import java.util.Date;
import java.util.Random;

public class GithubPlug implements GithubCalls {
  private Random random = new Random();

  @Override
  public boolean downloadRepository(String gitRepoURL) {
    return random.nextBoolean();
  }

  @Override
  public Integer countCommitsNumber(String gitRepoURL, Date from, Date to) {
    return random.nextInt(10);
  }
}
