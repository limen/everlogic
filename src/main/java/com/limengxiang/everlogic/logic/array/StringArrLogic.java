package com.limengxiang.everlogic.logic.array;

import com.limengxiang.everlogic.LogicParamBag;
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

    private enum StringArrOperator {
        equal,
        ne,
        contain,
        inside,
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        StringArrOperator operator;
        try {
            operator = StringArrOperator.valueOf(paramBag.getOperator().toLowerCase());
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
        List<String> leftList = toStringList(paramBag.getOperand(0));
        List<String> rightList = toStringList(paramBag.getOperand(1));
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
        List<String> leftList = toStringList(operand0);
        List<String> rightList = toStringList(operand1);
        return ArrLogicHelper.contains(leftList, rightList);
    }

    private List<String> toStringList(Object operand) throws Exception {
        if (operand instanceof Iterator) {
            return toStringList((Iterator) operand);
        }
        if (operand instanceof Collection) {
            return toStringList((Collection) operand);
        }
        throw new Exception("Unsupported array type:" + operand.getClass());
    }

    private List<String> toStringList(Iterator operand) throws Exception {
        List<String> strings = new ArrayList<>();
        Converter converter = getConverter();
        while (operand.hasNext()) {
            strings.add((String) converter.apply(operand.next()));
        }
        return strings;
    }

    private List<String> toStringList(Collection operand) throws Exception {
        List<String> strings = new ArrayList<>();
        Converter converter = getConverter();
        for (Object v : operand) {
            strings.add((String) converter.apply(v));
        }
        return strings;
    }
}
