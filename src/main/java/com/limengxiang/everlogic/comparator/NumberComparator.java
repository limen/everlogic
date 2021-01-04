package com.limengxiang.everlogic.comparator;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class NumberComparator implements Comparator {
    @Override
    public int apply(Object var0, Object var1) {
        if ((Double) var0 > (Double) var1) {
            return 1;
        } else if (var0.equals(var1)) {
            return 0;
        }
        return -1;
    }
}
