package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.comparator.BooleanComparator;
import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.BooleanConverter;

import java.util.List;

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

    private enum OpEnum {
        equal,
        ne,
    }

    @Override
    public boolean process(String op, List<Object> operands) {
        OpEnum opEnum;
        try {
            opEnum = OpEnum.valueOf(op.toLowerCase());
        } catch (Exception ex) {
            throw new RuntimeException("Unsupported operator:" + op);
        }
        Boolean leftOperand = (Boolean) getConverter().apply(operands.get(0));
        Boolean rightOperand = (Boolean) getConverter().apply(operands.get(1));
        int compare = getComparator().apply(leftOperand, rightOperand);
        switch (opEnum) {
            case equal:
                return compare == 0;
            case ne:
                return compare != 0;
            default:
                return false;
        }
    }
}
