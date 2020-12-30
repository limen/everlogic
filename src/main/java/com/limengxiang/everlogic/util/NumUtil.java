package com.limengxiang.everlogic.util;

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
}
