package ru.nsu.fit.g18214.yakovlev;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.apache.commons.math3.exception.NullArgumentException;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * This is a simple tree realisation that can iterate in two ways: as bfs and as dfs. Made on List.
 *
 * @param <T> Any comparable type.
 */
public class Tree<T extends Comparable<T>> implements Iterable<T> {

  public enum Iterate {
    DFS,
    BFS
  }

  private int cntOfDiffOper;
  private T val;
  private List<Tree<T>> sons;
  private Iterate iterateAs = Iterate.BFS;

  private T getVal() {
    return val;
  }

  private int compareTo(T o) {
    return this.getVal().compareTo(o);
  }

  /**
   * This is constructor for the tree, which will have root with argument val.
   *
   * @param val elem, which will be used as a root for a constructed tree.
   * @throws NullArgumentException if you put null as a val.
   */
  public Tree(T val) throws NullArgumentException {
    if (val == null) {
      throw new NullArgumentException();
    }
    this.val = val;
    this.sons = new ArrayList<>();
    this.cntOfDiffOper = 0;
  }

  private Tree(Tree<T> tree) {
    this.val = tree.getVal();
    this.sons = tree.getSons();
    this.iterateAs = tree.iterateAs;
    this.cntOfDiffOper = 0;
  }

  /**
   * It changes a flag, which is responsible for a way, how tree will be iterate.
   *
   * @param iterate how you want to iterate.
   * @throws NullArgumentException if you put null as an iterate.
   */
  public void setIterate(Iterate iterate) throws NullArgumentException {
    if (iterate == null) {
      throw new NullArgumentException();
    }
    this.iterateAs = iterate;
  }

  /**
   * Add a son to the current tree.
   *
   * @param elem An elem, which you want to add. Must not be null.
   * @throws NullArgumentException if you put null as an elem.
   */
  public void addElem(T elem) throws NullArgumentException {
    if (elem == null) {
      throw new NullArgumentException();
    }
    cntOfDiffOper++;
    sons.add(new Tree<>(elem));
  }

  /**
   * Delete an elem from sons of this tree.
   *
   * @param elem which you want to remove.
   * @throws NullArgumentException if you put null as an elem.
   */
  public void deleteElem(T elem) throws NullArgumentException {
    if (elem == null) {
      throw new NullArgumentException();
    }
    cntOfDiffOper++;
    int i = 0;
    for (Tree<T> son : sons) {
      if (son.compareTo(elem) == 0) {
        sons.remove(i);
        return;
      }
      i++;
    }
  }

  /**
   * This method returns a subTree with given elem or null. Remove it from tree sons.
   *
   * @param elem which will be in the root of given subtree.
   * @return Tree with given elem in root, or null if there is no elem with given name.
   * @throws NullArgumentException if you put null as an elem.
   */
  public Tree<T> getSubTree(T elem) throws NullArgumentException {
    if (elem == null) {
      throw new NullArgumentException();
    }
    cntOfDiffOper++;
    int i = 0;
    for (Tree<T> el : sons) {
      if (el.compareTo(elem) == 0) {
        sons.remove(i);
        return el;
      }
      i++;
    }
    return null;
  }

  /**
   * This method add a subTree to the current tree sons.
   *
   * @param subTree which we want to add.
   * @throws NullArgumentException if you put null as a subtree.
   */
  public void addSubTree(Tree<T> subTree) throws NullArgumentException {
    if (subTree == null) {
      throw new NullArgumentException();
    }
    cntOfDiffOper++;
    sons.add(new Tree<>(subTree));
  }

  private List<Tree<T>> getSons() {
    return sons;
  }

  private Iterator<T> dfs() throws ConcurrentModificationException {
    return new Iterator<>() {
      private Stack<Tree<T>> stack = new Stack<>();
      private boolean firstStep = true;
      private int save = cntOfDiffOper;

      @Override
      public boolean hasNext() {
        return !stack.empty() || firstStep;
      }

      @Override
      public T next() {
        if (save != cntOfDiffOper) {
          throw new ConcurrentModificationException();
        }
        if (firstStep) {
          firstStep = false;
          for (int j = sons.size() - 1; j >= 0; j--) {
            stack.push(sons.get(j));
          }
          return val;
        }
        Tree<T> ret = stack.pop();
        for (int j = ret.getSons().size() - 1; j >= 0; j--) {
          stack.push(ret.getSons().get(j));
        }
        return ret.getVal();
      }
    };
  }

  private Iterator<T> bfs() throws ConcurrentModificationException {
    return new Iterator<>() {
      private List<Tree<T>> queue = new ArrayList<>();
      private boolean firstStep = true;
      private int save = cntOfDiffOper;

      @Override
      public boolean hasNext() {
        return firstStep || queue.size() > 0;
      }

      @Override
      public T next() {
        if (save != cntOfDiffOper) {
          throw new ConcurrentModificationException();
        }
        if (firstStep) {
          firstStep = false;
          queue.addAll(sons);
          return val;
        }
        Tree<T> next = queue.get(0);
        queue.addAll(next.getSons());
        queue.remove(0);
        return next.getVal();
      }
    };
  }

  /**
   * This method returns an Iterator bases on a value 'iterateAs'.
   *
   * @return If iterateAs equivalent to Iterator.DFS -> returns DFS. If iterateAs equivalent to
   *     Iterator.BFS -> returns BFS.
   */
  @Override
  @NonNull
  public Iterator<T> iterator() throws ConcurrentModificationException {
    if (iterateAs == Iterate.DFS) {
      return dfs();
    } else {
      return bfs();
    }
  }
}
