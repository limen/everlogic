package com.limengxiang.everlogic.converter;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class BooleanConverter implements Converter {
    @Override
    public Boolean apply(Object var) {
        if (var == null) {
            return false;
        }
        if (var instanceof Boolean) {
            return (Boolean) var;
        }
        if (var instanceof String) {
            return Boolean.valueOf((String) var);
        }
        return (boolean) var;
    }
}
