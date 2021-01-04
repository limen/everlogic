package com.limengxiang.everlogic.converter;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class NumberConverter implements Converter {
    @Override
    public Double apply(Object var) throws Exception {
        if (var instanceof Double) {
            return (Double) var;
        }
        if (var instanceof String) {
            return Double.valueOf((String) var);
        }
        if (var instanceof Integer) {
            return ((Integer) var).doubleValue();
        }
        return Double.valueOf(String.valueOf(var));
    }
}
