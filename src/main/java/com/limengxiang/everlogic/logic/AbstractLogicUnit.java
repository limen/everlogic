package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.ILogicUnit;
import com.limengxiang.everlogic.LogicEvaluator;
import com.limengxiang.everlogic.LogicUnitFactoryContainer;
import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.formatter.Formatter;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public abstract class AbstractLogicUnit implements ILogicUnit {

    protected Converter converter;

    protected Comparator comparator;

    protected Formatter formatter;

    protected LogicEvaluator evaluator;

    protected LogicUnitFactoryContainer logicUnitFactoryContainer;

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

    public void setLogicUnitFactoryContainer(LogicUnitFactoryContainer container) {
        logicUnitFactoryContainer = container;
    }

    public void setEvaluator(LogicEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public LogicEvaluator getEvaluator() {
        return evaluator;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public LogicUnitFactoryContainer getLogicUnitFactoryContainer() {
        return logicUnitFactoryContainer;
    }
}
