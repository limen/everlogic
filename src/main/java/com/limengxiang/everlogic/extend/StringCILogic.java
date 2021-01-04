package com.limengxiang.everlogic.extend;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class StringCILogic implements LogicUnit {

    private enum Operator {
        equal,
        ne,
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        Operator operator;
        try {
            operator = Operator.valueOf(paramBag.getOperator());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }
        String left = ((String) paramBag.getOperand(0)).toLowerCase();
        String right = ((String) paramBag.getOperand(1)).toLowerCase();
        switch (operator) {
            case equal:
                return left.equals(right);
            case ne:
                return !left.equals(right);
            default:
                return false;
        }
    }
}
