package com.limengxiang.everlogic.comparator;

import java.util.*;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 * 可以指定比较日期的某部分，如只比较年、月、日
 *
 */
public class CalenderComparator implements Comparator {

    /**
     * 需比较的字段
     */
    private List<Integer> fields;

    public CalenderComparator() {
        fields = Arrays.asList(
                Calendar.YEAR,
                Calendar.MONTH,
                Calendar.DAY_OF_MONTH,
                Calendar.HOUR_OF_DAY,
                Calendar.MINUTE,
                Calendar.SECOND
        );
    }

    @Override
    public int apply(Object var0, Object var1) {
        Calendar cal0 = Calendar.getInstance();
        cal0.setTime((Date) var0);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime((Date) var1);
        fields.sort(java.util.Comparator.naturalOrder());
        int i = 0;
        for (Integer field : fields) {
            i = cal0.get(field) - cal1.get(field);
            if (i != 0) {
                break;
            }
        }
        return i;
    }

    public void setFields(List<Integer> fields) {
        this.fields = fields;
    }
}
