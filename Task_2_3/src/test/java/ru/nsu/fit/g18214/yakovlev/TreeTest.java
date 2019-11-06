package ru.nsu.fit.g18214.yakovlev;

import org.junit.Assert;
import org.junit.Test;

public class TreeTest {
  @Test
  public void test1() {
    Tree<String> tree = new Tree<String>("NGU");
    tree.addElem("FIT");
    tree.addElem("MMF");
    tree.addElem("FEN");
    int cnt = 0;
    for (String name: tree) {
      switch (cnt) {
        case 0:
          Assert.assertEquals(name, "NGU"); break;
        case 1:
          Assert.assertEquals(name, "FIT"); break;
        case 2:
          Assert.assertEquals(name, "MMF"); break;
        case 3:
          Assert.assertEquals(name, "FEN"); break;
      }
      cnt++;
    }
    Tree<String> fit = tree.getSubTree("FIT");
    for (String name: tree) {
      switch (cnt) {
        case 0:
          Assert.assertEquals(name, "NGU"); break;
        case 1:
          Assert.assertEquals(name, "MMF"); break;
        case 2:
          Assert.assertEquals(name, "FEN"); break;
      }
      cnt++;
    }
    fit.addElem("Old");
    fit.addElem("New");
    tree.addSubTree(fit);
    for (String name: tree) {
      switch (cnt) {
        case 0:
          Assert.assertEquals(name, "NGU"); break;
        case 1:
          Assert.assertEquals(name, "MMF"); break;
        case 2:
          Assert.assertEquals(name, "FEN"); break;
        case 3:
          Assert.assertEquals(name, "FIT"); break;
        case 4:
          Assert.assertEquals(name, "Old"); break;
        case 5:
          Assert.assertEquals(name, "New"); break;
      }
      cnt++;
    }
    fit.deleteElem("Old");
    for (String name: tree) {
      switch (cnt) {
        case 0:
          Assert.assertEquals(name, "NGU"); break;
        case 1:
          Assert.assertEquals(name, "MMF"); break;
        case 2:
          Assert.assertEquals(name, "FEN"); break;
        case 3:
          Assert.assertEquals(name, "FIT"); break;
        case 4:
          Assert.assertEquals(name, "New"); break;
      }
      cnt++;
    }
    tree.setIterateAsDfs(true);
    Tree<String> mmf = tree.getSubTree("MMF");
    Tree<String> fen = tree.getSubTree("FEN");
    fen.addElem("Fen old");
    fen.addElem("Fen new");
    tree.addSubTree(fen);
    mmf.addElem("Mmf prog");
    mmf.addElem("Mmf math");
    tree.addSubTree(mmf);
    for (String name: tree) {
      switch (cnt) {
        case 0:
          Assert.assertEquals(name, "NGU");
          break;
        case 1:
          Assert.assertEquals(name, "FIT");
          break;
        case 2:
          Assert.assertEquals(name, "New");
          break;
        case 3:
          Assert.assertEquals(name, "FEN");
          break;
        case 4:
          Assert.assertEquals(name, "Fen old");
          break;
        case 5:
          Assert.assertEquals(name, "Fen new");
          break;
        case 6:
          Assert.assertEquals(name, "MMF");
          break;
        case 7:
          Assert.assertEquals(name, "Mmf prog");
          break;
        case 8:
          Assert.assertEquals(name, "Mmf math");
          break;
      }
      cnt++;
    }
  }
}
