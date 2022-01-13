package com.limengxiang.everlogic.converter;

import com.limengxiang.everlogic.util.StrUtil;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class NumberConverter implements Converter {
    @Override
    public Double apply(Object var) {
        if (var instanceof Double) {
            return (Double) var;
        }
        if (var instanceof String) {
            return StrUtil.isBlank((String) var) ? null : Double.valueOf((String) var);
        }
        if (var instanceof Integer) {
            return ((Integer) var).doubleValue();
        }
        if (var instanceof Float) {
            return ((Float) var).doubleValue();
        }
        return null;
    }
}
