package com.limengxiang.everlogic.converter;

import com.limengxiang.everlogic.util.StrUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class DateConverter implements Converter {
    @Override
    public Object apply(Object var) {
        if (var instanceof Date) {
            return var;
        }

        if (var == null) {
            return null;
        }

        if (StrUtil.isBlank((String) var)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(String.valueOf(var));
        } catch (ParseException e) {
            throw new RuntimeException("parse date error:"+e.getMessage(), e);
        }
    }
}
