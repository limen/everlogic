package com.limengxiang.everlogic.comparator;

import java.util.Date;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class DateComparator implements Comparator {
    @Override
    public int apply(Object var0, Object var1) {
        if (((Date) var0).after((Date) var1)) {
            return 1;
        }
        if (((Date) var0).before((Date) var1)) {
            return -1;
        }
        return 0;
    }
}
