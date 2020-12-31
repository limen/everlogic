package com.limengxiang.everlogic.logic.array;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.util.NumUtil;

import java.util.List;
import java.util.Map;

public class NumberArrLogic implements LogicUnit {

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
        List<Double> leftList = NumUtil.toDoubleList((List) paramBag.getOperand(0));
        List<Double> rightList = NumUtil.toDoubleList((List) paramBag.getOperand(1));
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
        List<Double> leftList = NumUtil.toDoubleList((List) operand0);
        List<Double> rightList = NumUtil.toDoubleList((List) operand1);
        return ArrLogicHelper.contains(leftList, rightList);
    }
}
