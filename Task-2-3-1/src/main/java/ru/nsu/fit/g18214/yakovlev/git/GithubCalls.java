package ru.nsu.fit.g18214.yakovlev.git;

import java.util.Date;

public interface GithubCalls {

  boolean downloadRepository(String gitRepoURL);

  Integer countCommitsNumber(String gitRepoURL, Date from, Date to);

}
