package com.limengxiang.everlogic.logic.bool;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;

public class BoolLogic implements LogicUnit {

    private enum BoolOperator {
        equal,
        ne,
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        BoolOperator operator;
        try {
            operator = BoolOperator.valueOf(paramBag.getOperator().toLowerCase());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }
        Boolean leftOperand = (Boolean) paramBag.getOperands().get(0);
        Boolean rightOperand = (Boolean) paramBag.getOperands().get(1);
        switch (operator) {
            case equal:
                return (leftOperand != null && leftOperand.equals(rightOperand)) || (leftOperand == rightOperand);
            case ne:
                return (leftOperand != null && !leftOperand.equals(rightOperand)) || (leftOperand != rightOperand);
            default:
                return false;
        }
    }
}
