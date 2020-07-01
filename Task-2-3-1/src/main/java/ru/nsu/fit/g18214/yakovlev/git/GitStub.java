package ru.nsu.fit.g18214.yakovlev.git;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GitStub implements GitService {

  @Override
  public List<String> downloadRepository(String gitRepoURL) {
    return new ArrayList<>();
  }

  @Override
  public Integer countCommitsNumber(String gitRepoURL, Date from, Date to) {
    return 5;
  }
}
