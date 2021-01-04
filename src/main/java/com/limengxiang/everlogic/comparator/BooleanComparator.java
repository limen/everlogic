package com.limengxiang.everlogic.comparator;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class BooleanComparator implements Comparator {
    @Override
    public int apply(Object var0, Object var1) {
        int intVar0 = (Boolean) var0 ? 1 : 0;
        int intVar1 = (Boolean) var1 ? 1 : 0;
        return intVar0 - intVar1;
    }
}
