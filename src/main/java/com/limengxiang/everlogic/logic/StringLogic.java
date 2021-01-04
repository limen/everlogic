package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.comparator.StringComparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.StringConverter;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class StringLogic extends AbstractLogicUnit {

    @Override
    public Converter getDefaultConverter() {
        return new StringConverter();
    }

    @Override
    public Comparator getDefaultComparator() {
        return new StringComparator();
    }

    private enum StringOperator {
        gt,
        gte,
        lt,
        lte,
        equal,
        ne,
        contain,
        inside,
        start_with,
        end_with,
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        StringOperator operator;
        try {
            operator = StringOperator.valueOf(paramBag.getOperator());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }
        String leftOperand = (String) getConverter().apply(paramBag.getOperands().get(0));
        String rightOperand = (String) getConverter().apply(paramBag.getOperands().get(1));
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
            case start_with:
                return leftOperand != null && rightOperand != null && leftOperand.startsWith(rightOperand);
            case end_with:
                return leftOperand != null && rightOperand != null && leftOperand.endsWith(rightOperand);
            case contain:
                return leftOperand != null && rightOperand != null && leftOperand.contains(rightOperand);
            case inside:
                return leftOperand != null && rightOperand != null && rightOperand.contains(leftOperand);
            default:
                return false;
        }
    }
}
