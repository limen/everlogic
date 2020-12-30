package com.limengxiang.everlogic.logic.string;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.util.StrUtil;

public class StringLogic implements LogicUnit {

    private enum StringOperator {
        gt,
        gte,
        lt,
        lte,
        equal,
        ne,
        contain,
        inside,
        start_with,
        end_with,
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        StringOperator operator;
        try {
            operator = StringOperator.valueOf(paramBag.getOperator());
        } catch (Exception ex) {
            throw new Exception("Unsupported operator:" + paramBag.getOperator());
        }
        String leftOperand = (String) paramBag.getOperands().get(0);
        String rightOperand = (String) paramBag.getOperands().get(1);
        switch (operator) {
            case gt:
                return StrUtil.compare(leftOperand, rightOperand) > 0;
            case gte:
                return StrUtil.compare(leftOperand, rightOperand) >= 0;
            case lt:
                return StrUtil.compare(leftOperand, rightOperand) < 0;
            case lte:
                return StrUtil.compare(leftOperand, rightOperand) <= 0;
            case equal:
                return StrUtil.compare(leftOperand, rightOperand) == 0;
            case ne:
                return StrUtil.compare(leftOperand, rightOperand) != 0;
            case start_with:
                return leftOperand != null && rightOperand != null && leftOperand.startsWith(rightOperand);
            case end_with:
                return leftOperand != null && rightOperand != null && leftOperand.endsWith(rightOperand);
            case contain:
                return leftOperand != null && rightOperand != null && leftOperand.contains(rightOperand);
            case inside:
                return leftOperand != null && rightOperand != null && rightOperand.contains(leftOperand);
            default:
                return false;
        }
    }
}
