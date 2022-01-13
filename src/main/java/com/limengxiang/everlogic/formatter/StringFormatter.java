package com.limengxiang.everlogic.formatter;

import com.limengxiang.everlogic.FormatterConst;

public class StringFormatter extends AbstractFormatter<String> {

    @Override
    public String apply(String v) {
        if (type.equals(FormatterConst.Strings.TYPE_LOWER)) {
            return v.toLowerCase();
        }
        if (type.equals(FormatterConst.Strings.TYPE_UPPER)) {
            return v.toUpperCase();
        }
        if (type.equals(FormatterConst.Strings.TYPE_SUB)) {
            return v.substring((int)args.get(0), (int)args.get(1));
        }
        return v;
    }
}
