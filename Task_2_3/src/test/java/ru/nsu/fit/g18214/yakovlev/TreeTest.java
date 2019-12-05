package ru.nsu.fit.g18214.yakovlev;

import java.util.ConcurrentModificationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.g18214.yakovlev.Tree.Iterate;

public class TreeTest {
  @Test
  public void testNull() {
    try{
      Tree<String> tree = new Tree<>(null);
      Assert.fail();
    } catch (NullArgumentException e) {
      Assert.assertTrue(true);
    }
  }

  @Test
  public void testBFS() {
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
  }

  @Test
  public void testCME() {
    Tree<String> tree = new Tree<String>("NGU");
    tree.addElem("FIT");
    tree.addElem("MMF");
    tree.addElem("FEN");
    boolean CME = false;
    try {
      for (String name : tree) {
        tree.addElem("keklolERROR");
      }
    } catch (ConcurrentModificationException e) {
      CME = true;
    }
    Assert.assertTrue(CME);
  }
  @Test
  public void testGetSubTree() {
    Tree<String> tree = new Tree<String>("NGU");
    tree.addElem("FIT");
    tree.addElem("MMF");
    tree.addElem("FEN");
    int cnt = 0;
    Tree<String> fit = tree.getSubTree("FIT");
    fit.addElem("Old");
    fit.addElem("New");
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
    cnt = 0;
    for (String name: fit) {
      switch (cnt) {
        case 0:
          Assert.assertEquals(name, "FIT"); break;
        case 1:
          Assert.assertEquals(name, "Old"); break;
        case 2:
          Assert.assertEquals(name, "New"); break;
      }
      cnt++;
    }
  }
  @Test
  public void testAddElemFromGetSubTree() {
    Tree<String> tree = new Tree<>("NGU");
    tree.addElem("FIT");
    tree.addElem("MMF");
    tree.addElem("FEN");
    int cnt = 0;
    Tree<String> fit = tree.getSubTree("FIT");
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
  }
  @Test
  public void testDelete() {
    Tree<String> tree = new Tree<>("NGU");
    tree.addElem("FIT");
    tree.addElem("MMF");
    tree.addElem("FEN");
    int cnt = 0;
    Tree<String> fit = tree.getSubTree("FIT");
    fit.addElem("Old");
    fit.addElem("New");
    tree.addSubTree(fit);
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
  }
  @Test
  public void testDFS() {
    Tree<String> tree = new Tree<>("NGU");
    tree.addElem("FIT");
    tree.addElem("MMF");
    tree.addElem("FEN");
    int cnt = 0;
    Tree<String> fit = tree.getSubTree("FIT");
    fit.addElem("New");
    tree.addSubTree(fit);
    tree.setIterate(Iterate.DFS);
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
