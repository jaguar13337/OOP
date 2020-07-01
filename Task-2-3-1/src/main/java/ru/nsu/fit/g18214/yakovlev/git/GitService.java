package ru.nsu.fit.g18214.yakovlev.git;

import java.util.Date;
import java.util.List;

public interface GitService {

  /**
   * This method checks if repository up to date, or needs to be updated.
   * @param gitRepoURL repository URL
   * @return List of errors or empty list, if everything ok.
   */
  List<String> downloadRepository(String gitRepoURL) throws GitException;

  Integer countCommitsNumber(String gitRepoURL, Date from, Date to) throws GitException;

}
