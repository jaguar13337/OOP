package ru.nsu.fit.g18214.yakovlev;

import java.io.IOException;
import java.io.Reader;
import java.util.Vector;

public class FindSubstrings {

  /**
   * Find all occurrences of string 'subString' in the file 'fileName'.
   *
   * @param r get an opened reader to read a text
   * @param subString a substring that you want to find
   * @return int array with all occurrences. Can be empty.
   */
  public static int[] findSubstrings(Reader r, String subString) throws IOException {
    char[] concat = new char[subString.length() * 3 + 1];
    char[] string = subString.toCharArray();
    Vector<Integer> ans = new Vector<Integer>(0);
    System.arraycopy(string, 0, concat, 0, string.length);
    concat[string.length] = '$';
    int off = string.length + 1;
    int len = r.read(concat, off, subString.length() * 2);

    int offset = 0;

    while (len != -1) {
      int l = concat.length;
      int[] z = new int[l];

      getZ(concat, z);

      for (int i = off; i < l; ++i) {
        if (z[i] == subString.length()) {
          ans.add(i - subString.length() - 1 + offset);
          for (int j = i; j < i+subString.length(); j++) {
            concat[j] = '$';
          }
        }
      }

      System.arraycopy(concat, subString.length()+off, concat, off, subString.length());

      len = r.read(concat, subString.length()+off, subString.length());

      if (len < subString.length()) {
        for (int i = subString.length() + len + off; i < subString.length() * 3 + 1; i++) {
          concat[i] = '$';
        }
      }

      offset += subString.length();
    }

    int[] array = new int[ans.size()];

    for (int i = 0; i < ans.size(); i++) {
      array[i] = ans.get(i);
    }

    return array;
  }

  private static void getZ(char[] str, int[] z) {
    int n = str.length;
    int l = 0, r = 0;
    for (int i = 1; i < n; ++i) {
      if (i > r) {
        l = r = i;
        while (r < n && str[r - l] == str[r]) {
          r++;
        }
        z[i] = r - l;
        r--;
      } else {
        int k = i - l;
        if (z[k] < r - i + 1) {
          z[i] = z[k];
        } else {
          l = i;
          while (r < n && str[r - l] == str[r]) {
            r++;
          }
          z[i] = r - l;
          r--;
        }
      }
    }
  }
}
