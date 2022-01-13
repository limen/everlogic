package com.limengxiang.everlogic.extend;

import com.limengxiang.everlogic.ILogicUnit;

import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class StringCILogic implements ILogicUnit {

    private enum Operator {
        equal,
        ne,
    }

    @Override
    public boolean process(String op, List<Object> operands) {
        Operator operator;
        try {
            operator = Operator.valueOf(op);
        } catch (Exception ex) {
            throw new RuntimeException("Unsupported operator:" + op);
        }
        if (operands.get(0) == null && operands.get(1) == null) {
            return true;
        }
        if (operands.get(0) == null || operands.get(1) == null) {
            return false;
        }
        String s0 = (String) operands.get(0);
        String s1 = (String) operands.get(1);
        String left = s0.toLowerCase();
        String right = s1.toLowerCase();
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
