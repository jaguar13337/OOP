package ru.nsu.fit.g18214.yakovlev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Vector;

public class FindSubstrings {
    /**
     * Find all occurrences of string 'find' in the text 'text'.
     * @param text text, where you want to find all occurrences of String 'find'.
     * @param find a substring that you want to find
     * @return
     */

    public static int[] findSubstrings(String text, String find) {
        Vector<Integer> ans = new Vector<Integer>(0);
        //File file = new File(fileName);
        //BufferedReader br = new BufferedReader(new FileReader(file));
        //br.close();
        String concat = find + "$" + text;
        int l = concat.length();
        int Z[] = new int[l];
        getZ(concat, Z);
        for(int i = 0; i < l; ++i){
            if(Z[i] == find.length()){
                ans.add(i - find.length() - 1);
            }
        }
        int[] array = new int[ans.size()];
        for (int i = 0; i<ans.size(); i++)
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
