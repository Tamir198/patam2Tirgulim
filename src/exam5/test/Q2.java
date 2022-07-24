package test;

import java.util.Collections;
import java.util.List;

public class Q2 {
	  static int min;

    public static int H(String a, String b) {
        int sum = 0;
        int len = Math.min(a.toCharArray().length, b.toCharArray().length);

        for (int i = 0; i < len; i++) {
            if (a.toCharArray()[i] != b.toCharArray()[i])
                if (sum >= min) {
                    return sum;
                } else {
                    sum++;
                }
        }

        sum += Math.abs(a.length() - b.length());

        return sum;
    }

    
    // inefficent code. re-implement.
    public static int findMinH(List<String> array) {
        min = Integer.MAX_VALUE;
        int res;
        for (String a : array) {
            for (String b : array) {
                int len = Math.abs(b.length() - a.length());
                if(len >= min) continue;
                if (a != b) {
                    res = H(a, b);
                    if (min > res) {
                        min = res;
                    }
                }
            }
        }
        return min;
    }
}
