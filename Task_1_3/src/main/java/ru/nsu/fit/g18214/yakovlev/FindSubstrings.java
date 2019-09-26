package ru.nsu.fit.g18214.yakovlev;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class FindSubstrings {
    /**
     * Find all occurrences of string 'find' in the text 'text'.
     * @param fileName, where you want to find all occurrences of String 'find'.
     * @param find a substring that you want to find
     * @return
     */

    public static int[] findSubstrings(String fileName, String find) throws IOException{
        char[] text = new char[find.length() * 2];
        Vector<Integer> ans = new Vector<Integer>(0);
        File file = new File(fileName);
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));
        int len = 0;
        len = br.read(text, 0, find.length() * 2);
        int offset = 0;
        while (len != -1) {
            String concat = find + "$" + (new String(text));
            int l = concat.length();
            int Z[] = new int[l];
            getZ(concat, Z);
            for (int i = 0; i < l; ++i) {
                if (Z[i] == find.length()) {
                    ans.add(i - find.length() - 1 + offset);
                    for (int j = i - find.length() - 1; j < i - 1; j++)
                        text[j] = '$';
                }
            }
            for (int i = 0; i < find.length(); i++)
                text[i] = text[i + find.length()];
            len = br.read(text, find.length(), find.length());
            if (len < find.length())
                for (int i = find.length() + len; i < find.length()*2; i++)
                    text[i] = '$';
            offset += find.length();
        }
        int[] array = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            array[i] = ans.get(i);
        return array;
    }

    private static void getZ(String str, int[] Z) {
        int n = str.length();
        int L = 0, R = 0;
        for(int i = 1; i < n; ++i) {
            if(i > R){
                L = R = i;
                while(R < n && str.charAt(R - L) == str.charAt(R))
                    R++;
                Z[i] = R - L;
                R--;
            }
            else{
                int k = i - L;
                if(Z[k] < R - i + 1)
                    Z[i] = Z[k];
                else{
                    L = i;
                    while(R < n && str.charAt(R - L) == str.charAt(R))
                        R++;
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }
}
