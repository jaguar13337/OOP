package ru.nsu.fit.g18214.yakovlev.git;

import java.util.Date;

public class Github implements GithubService {

  @Override
  public boolean downloadRepository(String gitRepoURL) {
    return true;
  }

  @Override
  public Integer countCommitsNumber(String gitRepoURL, Date from, Date to) {
    return 5;
  }
}
