package com.limengxiang.everlogic.formatter;

import com.limengxiang.everlogic.FormatterConst;

public class NumberFormatter extends AbstractFormatter<Number> {
    @Override
    public Number apply(Number v) {
        if (type.equals(FormatterConst.Numbers.TYPE_ROUND)) {
            return Math.round(v.doubleValue());
        } else if (type.equals(FormatterConst.Numbers.TYPE_CEIL)) {
            return Math.ceil(v.doubleValue());
        } else if (type.equals(FormatterConst.Numbers.TYPE_CUT)) {
            int f = (int) args.get(0);
            double pow = Math.pow(10, f);
            return ((Double)(v.doubleValue() * pow)).intValue() / pow;
        }
        return v;
    }
}
