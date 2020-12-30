package com.limengxiang.everlogic.logic.number;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.util.NumUtil;

import java.util.List;

public class NumberLogic implements LogicUnit {

    private enum  NumberOperator {
        gt,
        gte,
        lt,
        lte,
        equal,
        ne,
    }

    public boolean process(LogicParamBag paramBag) throws Exception {
        NumberOperator operator;
        try {
            operator = NumberOperator.valueOf(paramBag.getOperator().toLowerCase());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }
        checkOperand(operator, paramBag.getOperands());
        Double leftOperand = NumUtil.toDouble(paramBag.getOperands().get(0));
        Double rightOperand = NumUtil.toDouble(paramBag.getOperands().get(1));
        switch (operator) {
            case gt:
                return leftOperand > rightOperand;
            case gte:
                return leftOperand >= rightOperand;
            case lt:
                return leftOperand < rightOperand;
            case lte:
                return leftOperand <= rightOperand;
            case equal:
                return leftOperand.equals(rightOperand);
            case ne:
                return !leftOperand.equals(rightOperand);
            default:
                return false;
        }
    }

    private void checkOperand(NumberOperator operator, List<Object> operands) throws Exception {
        switch (operator) {
            case gt:
            case gte:
            case lt:
            case lte:
            case equal:
            case ne:
                if (operands == null || operands.size() != 2) {
                    throw new Exception("Operands should contain 2 elements");
                }
            default:
                break;
        }
    }
}
