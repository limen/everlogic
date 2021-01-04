package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.comparator.BooleanComparator;
import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.BooleanConverter;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class BoolLogic extends AbstractLogicUnit {

    @Override
    public Converter getDefaultConverter() {
        return new BooleanConverter();
    }

    @Override
    public Comparator getDefaultComparator() {
        return new BooleanComparator();
    }

    private enum BoolOperator {
        equal,
        ne,
        gt,
        gte,
        lt,
        lte
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        BoolOperator operator;
        try {
            operator = BoolOperator.valueOf(paramBag.getOperator().toLowerCase());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }
        Boolean leftOperand = (Boolean) getConverter().apply(paramBag.getOperands().get(0));
        Boolean rightOperand = (Boolean) getConverter().apply(paramBag.getOperands().get(1));
        int compare = getComparator().apply(leftOperand, rightOperand);
        switch (operator) {
            case equal:
                return compare == 0;
            case ne:
                return compare != 0;
            case gt:
                return compare > 0;
            case lt:
                return compare < 0;
            case gte:
                return compare >= 0;
            case lte:
                return compare <= 0;
            default:
                return false;
        }
    }
}
