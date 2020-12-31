package com.limengxiang.everlogic.util;

import java.util.*;

public class NumUtil {

    public static Double toDouble(Object v) {
        if (v instanceof Double) {
            return (Double) v;
        }
        if (v instanceof String) {
            return Double.valueOf((String) v);
        }
        if (v instanceof Integer) {
            return ((Integer) v).doubleValue();
        }
        return Double.valueOf(String.valueOf(v));
    }

    public static List<Double> toDoubleList(List arr) {
        List<Double> doubles = new ArrayList<>();
        for (Object elem : arr) {
            doubles.add(toDouble(elem));
        }
        return doubles;
    }
}
