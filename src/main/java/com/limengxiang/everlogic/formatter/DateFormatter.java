package com.limengxiang.everlogic.formatter;

import com.limengxiang.everlogic.FormatterConst;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter extends AbstractFormatter<Date> {

    @Override
    public Date apply(Date v) {
        if (type.equals(FormatterConst.Dates.TYPE_FORMAT)) {
            String fmt = (String) args.get(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmt);
            String fmtDate = simpleDateFormat.format(v);
            try {
                return simpleDateFormat.parse(fmtDate);
            } catch (ParseException e) {
                throw new RuntimeException("parse date error:"+e.getMessage(), e);
            }
        }

        return v;
    }
}
