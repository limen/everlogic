package com.limengxiang.everlogic.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class DateConverter implements Converter {
    @Override
    public Object apply(Object var) throws Exception {
        if (var instanceof Date) {
            return (Date) var;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(String.valueOf(var));
    }
}
