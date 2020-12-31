package com.limengxiang.everlogic.util;

import java.util.ArrayList;
import java.util.List;

public class StrUtil {

    public static int compare(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        }
        if (s1 == null) {
            return -1;
        }
        if (s2 == null) {
            return 1;
        }
        return compareChars(s1, s2);
    }

    public static List<String> toStringList(List list) {
        List<String> strings = new ArrayList<>();
        for (Object v : list) {
            strings.add(String.valueOf(v));
        }
        return strings;
    }

    private static int compareChars(String s1, String s2) {
        char[] seq1 = s1.toCharArray();
        char[] seq2 = s2.toCharArray();
        int i = Math.min(seq1.length, seq2.length);
        for (int j=0; j<i; j++) {
            if (seq1[j] < seq2[j]) {
                return -1;
            } else if (seq1[j] > seq2[j]) {
                return 1;
            }
        }
        return seq1.length - seq2.length;
    }
}
