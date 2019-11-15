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
 * @param <T> Any comparable type.
 */
public class Tree<T extends Comparable<T>> implements Iterable<T> {

  public enum Iterate {
    DFS,
    BFS
  }

  private T val;
  private List<Tree<T>> sons;
  private Iterate iterateAs = Iterate.BFS;
  private boolean iterating;

  private T getVal() {
    return val;
  }

  private int compareTo(T o) {
    return this.getVal().compareTo(o);
  }

  /**
   *
   * @param val shouldn't be == null
   */
  public Tree(T val) {
    if (val == null) {
      throw new NullArgumentException();
    }
    this.val = val;
    this.sons = new ArrayList<>();
    this.iterating = false;
  }

  private Tree(Tree<T> tree) {
    this.val = tree.getVal();
    this.sons = tree.getSons();
    this.iterating = false;
    this.iterateAs = tree.iterateAs;
  }

  /**
   * It changes a flag, which is responsible for a way, how tree will be iterate.
   * @param iterate if you want to iterate as dfs -> set true. bfs -> false
   */
  public void setIterate(Iterate iterate) {
    this.iterateAs = iterate;
  }

  /**
   * Add a son to the current tree.
   * @param elem An elem, which you want to add. Must not be null
   */
  public void addElem(T elem) {
    if (iterating) {
      throw new ConcurrentModificationException();
    }
    sons.add(new Tree<>(elem));
  }


  /**
   * Delete an elem from sons of this tree.
   * @param elem which you want to remove.
   */
  public void deleteElem(T elem) {
    if (iterating) {
      throw new ConcurrentModificationException();
    }
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
   * This method returns a subTree with given elem. Remove it from tree sons.
   * @param elem which will be in the root of given subtree.
   * @return Tree with given elem in root.
   */
  public Tree<T> getSubTree(T elem) {
    if (iterating) {
      throw new ConcurrentModificationException();
    }
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
   * @param subTree which we want to add.
   */
  public void addSubTree(Tree<T> subTree) {
    if (iterating) {
      throw new ConcurrentModificationException();
    }
    sons.add(new Tree<>(subTree));
  }

  private List<Tree<T>> getSons() {
    return sons;
  }

  private Iterator<T> dfs() {
    return new Iterator<>() {
      private Stack<Tree<T>> stack = new Stack<>();
      private boolean firstStep = true;

      @Override
      public boolean hasNext() {

        iterating = !stack.empty() || firstStep;
        return iterating;
      }

      @Override
      public T next() {
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


  private Iterator<T> bfs() {
    return new Iterator<>() {
      private List<Tree<T>> queue = new ArrayList<>();
      private boolean firstStep = true;

      @Override
      public boolean hasNext() {
        iterating = firstStep || queue.size() > 0;
        return iterating;

      }

      @Override
      public T next() {
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
   * This method returns an Iterator bases on a value 'iterateAsDfs'
   * @return If iterateAsDfs is set, returns an Iterator DFS, otherwise - BFS.
   */
  @Override
  @NonNull
  public Iterator<T> iterator() {
    if (iterateAs == Iterate.DFS)
      return dfs();
    else {
      return bfs();
    }
  }
}
