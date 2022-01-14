package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.comparator.NumberComparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.NumberConverter;

import java.util.List;

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

    private enum OpEnum {
        gt,
        gte,
        lt,
        lte,
        equal,
        ne,
        nil,
        not_nil,
    }


    public boolean process(String op, List<Object> operands) {
        OpEnum opEnum;
        try {
            opEnum = OpEnum.valueOf(op.toLowerCase());
        } catch (Exception ex) {
            throw new RuntimeException("Unsupported operator:" + op);
        }

        Double leftOperand = (Double) getConverter().apply(operands.get(0));
        Double rightOperand = operands.size() > 1 ? (Double) getConverter().apply(operands.get(1)) : null;

        if (OpEnum.nil.equals(opEnum)) {
            return leftOperand == null;
        }
        if (OpEnum.not_nil.equals(opEnum)) {
            return leftOperand != null;
        }

        if (getFormatter() != null) {
            leftOperand = ((Number) getFormatter().apply(leftOperand)).doubleValue();
            rightOperand = ((Number) getFormatter().apply(rightOperand)).doubleValue();
        }

        int compare = getComparator().apply(leftOperand, rightOperand);
        switch (opEnum) {
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
