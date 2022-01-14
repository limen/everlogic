package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.comparator.DateComparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.DateConverter;

import java.util.Date;
import java.util.List;

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

    private enum OpEnum {
        equal,
        ne,
        lt,
        lte,
        gt,
        gte,
        nil,
        not_nil,
    }

    @Override
    public boolean process(String op, List<Object> operands) {
        OpEnum opEnum;
        try {
            opEnum = OpEnum.valueOf(op.toLowerCase());
        } catch (Exception ex) {
            throw new RuntimeException("Unsupported opEnum:" + op);
        }

        Date operand0 = (Date) getConverter().apply(operands.get(0));

        if (OpEnum.nil.equals(opEnum)) {
            return operand0 == null;
        }
        if (OpEnum.not_nil.equals(opEnum)) {
            return operand0 != null;
        }

        Date operand1 = (Date) getConverter().apply(operands.get(1));
        if (getFormatter() != null) {
            operand0 = (Date) getFormatter().apply(operand0);
            operand1 = (Date) getFormatter().apply(operand1);
        }
        int compare = getComparator().apply(operand0, operand1);
        switch (opEnum) {
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
