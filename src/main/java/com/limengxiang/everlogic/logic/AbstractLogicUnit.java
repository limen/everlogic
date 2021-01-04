package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.converter.Converter;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public abstract class AbstractLogicUnit implements LogicUnit {

    protected Converter converter;

    private Comparator comparator;

    public Converter getConverter() {
        if (converter == null) {
            converter = getDefaultConverter();
        }
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public abstract Converter getDefaultConverter();

    public abstract Comparator getDefaultComparator();

    public Comparator getComparator() {
        if (comparator == null) {
            comparator = getDefaultComparator();
        }
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }
}
