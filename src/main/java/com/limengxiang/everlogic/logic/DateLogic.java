package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.comparator.DateComparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.DateConverter;

import java.util.Date;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class DateLogic extends AbstractLogicUnit {
    @Override
    public Converter getDefaultConverter() {
        return new DateConverter();
    }

    @Override
    public Comparator getDefaultComparator() {
        return new DateComparator();
    }

    private enum Operator {
        equal,
        ne,
        lt,
        lte,
        gt,
        gte
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        Operator operator;
        try {
            operator = Operator.valueOf(paramBag.getOperator().toLowerCase());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }

        Date operand0 = (Date) getConverter().apply(paramBag.getOperand(0));
        Date operand1 = (Date) getConverter().apply(paramBag.getOperand(1));
        int compare = getComparator().apply(operand0, operand1);
        switch (operator) {
            case equal:
                return compare == 0;
            case ne:
                return compare != 0;
            case lte:
                return compare <= 0;
            case gt:
                return compare > 0;
            case gte:
                return compare >= 0;
            case lt:
                return compare < 0;
            default:
                return false;
        }
    }
}
