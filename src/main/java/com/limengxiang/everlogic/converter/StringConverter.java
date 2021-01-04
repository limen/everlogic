package com.limengxiang.everlogic.converter;

import java.text.NumberFormat;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class StringConverter implements Converter {
    @Override
    public String apply(Object var) {
        if (var == null) {
            return null;
        }
        if (var instanceof String) {
            return (String) var;
        }
        if (var instanceof Double || var instanceof Float) {
            // 不以科学计数法表示浮点数
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            return nf.format(var);
        }
        return String.valueOf(var);
    }
}
