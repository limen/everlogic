package com.limengxiang.everlogic.logic.array;

import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.comparator.StringComparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.StringConverter;
import com.limengxiang.everlogic.logic.AbstractLogicUnit;

import java.util.*;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class StringArrLogic extends AbstractLogicUnit {

    @Override
    public Converter getDefaultConverter() {
        return new StringConverter();
    }

    @Override
    public Comparator getDefaultComparator() {
        return new StringComparator();
    }

    private enum OpEnum {
        equal,
        ne,
        contain,
        inside,
    }

    @Override
    public boolean process(String op, List<Object> operands) {
        OpEnum opEnum;
        try {
            opEnum = OpEnum.valueOf(op.toLowerCase());
        } catch (Exception ex) {
            throw new RuntimeException("Unsupported operator:" + op);
        }
        switch (opEnum) {
            case equal:
                return equals(operands);
            case ne:
                return !equals(operands);
            case contain:
                return contains(operands.get(0), operands.get(1));
            case inside:
                return contains(operands.get(1), operands.get(0));
        }
        return false;
    }

    private boolean equals(List<Object> operands) {
        if (!(operands.get(0) instanceof List)) {
            throw new RuntimeException("Operand#0 must be a list");
        }
        if (!(operands.get(1) instanceof List)) {
            throw new RuntimeException("Operand#1 must be a list");
        }
        List<String> leftList = toStringList(operands.get(0));
        List<String> rightList = toStringList(operands.get(1));
        if (leftList.size() != rightList.size()) {
            return false;
        }
        Map<Object, Integer> leftElemCount = ArrLogicHelper.getElemCount(leftList);
        Map<Object, Integer> rightElemCount = ArrLogicHelper.getElemCount(rightList);
        return leftElemCount.equals(rightElemCount);
    }

    private boolean contains(Object operand0, Object operand1) {
        if (!(operand0 instanceof List)) {
            throw new RuntimeException("Operand#0 must be a list");
        }
        if (!(operand1 instanceof List)) {
            throw new RuntimeException("Operand#1 must be a list");
        }
        List<String> leftList = toStringList(operand0);
        List<String> rightList = toStringList(operand1);
        return ArrLogicHelper.contains(leftList, rightList);
    }

    private List<String> toStringList(Object operand) {
        if (operand instanceof Iterator) {
            return toStringList((Iterator) operand);
        }
        if (operand instanceof Collection) {
            return toStringList((Collection) operand);
        }
        throw new RuntimeException("Unsupported array type:" + operand.getClass());
    }

    private List<String> toStringList(Iterator operand) {
        List<String> strings = new ArrayList<>();
        Converter converter = getConverter();
        while (operand.hasNext()) {
            strings.add((String) converter.apply(operand.next()));
        }
        return strings;
    }

    private List<String> toStringList(Collection operand) {
        List<String> strings = new ArrayList<>();
        Converter converter = getConverter();
        for (Object v : operand) {
            strings.add((String) converter.apply(v));
        }
        return strings;
    }
}
