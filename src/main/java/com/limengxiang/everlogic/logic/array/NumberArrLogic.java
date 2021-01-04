package com.limengxiang.everlogic.logic.array;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.comparator.Comparator;
import com.limengxiang.everlogic.comparator.NumberComparator;
import com.limengxiang.everlogic.converter.Converter;
import com.limengxiang.everlogic.converter.NumberConverter;
import com.limengxiang.everlogic.logic.AbstractLogicUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private enum NumberArrOperator {
        equal,
        ne,
        contain,
        inside,
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        NumberArrOperator operator;
        try {
            operator = NumberArrOperator.valueOf(paramBag.getOperator().toLowerCase());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }
        switch (operator) {
            case equal:
                return equals(paramBag);
            case ne:
                return !equals(paramBag);
            case contain:
                return contains(paramBag.getOperand(0), paramBag.getOperand(1));
            case inside:
                return contains(paramBag.getOperand(1), paramBag.getOperand(0));
        }
        return false;
    }

    private boolean equals(LogicParamBag paramBag) throws Exception {
        if (!(paramBag.getOperand(0) instanceof List)) {
            throw new Exception("Operand 0 must be a list");
        }
        if (!(paramBag.getOperand(1) instanceof List)) {
            return false;
        }
        List<Double> leftList = toDoubleList(paramBag.getOperand(0));
        List<Double> rightList = toDoubleList(paramBag.getOperand(1));
        if (leftList.size() != rightList.size()) {
            return false;
        }
        Map<Object, Integer> leftElemCount = ArrLogicHelper.getElemCount(leftList);
        Map<Object, Integer> rightElemCount = ArrLogicHelper.getElemCount(rightList);
        return leftElemCount.equals(rightElemCount);
    }

    private boolean contains(Object operand0, Object operand1) throws Exception {
        if (!(operand0 instanceof List)) {
            throw new Exception("Operand 0 must be a list");
        }
        if (!(operand1 instanceof List)) {
            return ((List) operand0).contains(operand1);
        }
        List<Double> leftList = toDoubleList(operand0);
        List<Double> rightList = toDoubleList(operand1);
        return ArrLogicHelper.contains(leftList, rightList);
    }

    private List<Double> toDoubleList(Object operand) throws Exception {
        List<Double> doubles = new ArrayList<>();
        Converter converter = getConverter();
        for (Object v : (List) operand) {
            doubles.add((Double) converter.apply(v));
        }
        return doubles;
    }
}
