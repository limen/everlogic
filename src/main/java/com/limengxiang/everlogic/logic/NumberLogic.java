package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.comparator.NumberComparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.NumberConverter;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class NumberLogic extends AbstractLogicUnit {

    @Override
    public Converter getDefaultConverter() {
        return new NumberConverter();
    }

    @Override
    public Comparator getDefaultComparator() {
        return new NumberComparator();
    }

    private enum  NumberOperator {
        gt,
        gte,
        lt,
        lte,
        equal,
        ne,
    }


    public boolean process(LogicParamBag paramBag) throws Exception {
        NumberOperator operator;
        try {
            operator = NumberOperator.valueOf(paramBag.getOperator().toLowerCase());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }
        Double leftOperand = (Double) getConverter().apply(paramBag.getOperands().get(0));
        Double rightOperand = (Double) getConverter().apply(paramBag.getOperands().get(1));
        int compare = getComparator().apply(leftOperand, rightOperand);
        switch (operator) {
            case gt:
                return compare > 0;
            case gte:
                return compare >= 0;
            case lt:
                return compare < 0;
            case lte:
                return compare <= 0;
            case equal:
                return compare == 0;
            case ne:
                return compare != 0;
            default:
                return false;
        }
    }
}
