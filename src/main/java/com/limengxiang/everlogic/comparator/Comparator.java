package com.limengxiang.everlogic.comparator;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public interface Comparator {
    /**
     * @param var0
     * @param var1
     * @return positive if var0 > var1, zero if var0 == var1, negative otherwise
     */
    int apply(Object var0, Object var1);
}
