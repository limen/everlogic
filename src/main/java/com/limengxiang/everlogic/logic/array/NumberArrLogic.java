package com.limengxiang.everlogic.logic.array;

import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.comparator.NumberComparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.NumberConverter;
import com.limengxiang.everlogic.logic.AbstractLogicUnit;

import java.util.*;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class NumberArrLogic extends AbstractLogicUnit {

    @Override
    public Converter getDefaultConverter() {
        return new NumberConverter();
    }

    @Override
    public Comparator getDefaultComparator() {
        return new NumberComparator();
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
        List<Double> leftList = toDoubleList(operands.get(0));
        List<Double> rightList = toDoubleList(operands.get(1));
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
            throw new RuntimeException("Operand#0 must be a list");
        }
        List<Double> leftList = toDoubleList(operand0);
        List<Double> rightList = toDoubleList(operand1);
        return ArrLogicHelper.contains(leftList, rightList);
    }

    private List<Double> toDoubleList(Object operand) {
        if (operand instanceof Collection) {
            return toDoubleList((Collection) operand);
        }
        if (operand instanceof Iterator) {
            return toDoubleList((Iterator) operand);
        }
        throw new RuntimeException("Unsupported array type:" + operand.getClass());
    }

    private List<Double> toDoubleList(Iterator iterator) {
        List<Double> doubles = new ArrayList<>();
        Converter converter = getConverter();
        while (iterator.hasNext()) {
            doubles.add((Double) converter.apply(iterator.next()));
        }
        return doubles;
    }

    private List<Double> toDoubleList(Collection list) {
        List<Double> doubles = new ArrayList<>();
        Converter converter = getConverter();
        for (Object v : list) {
            doubles.add((Double) converter.apply(v));
        }
        return doubles;
    }
}
