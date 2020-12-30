package com.limengxiang.everlogic;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public final class LogicParamBag {

    private ParamTypeEnum paramType;

    private String operator;

    private List<Object> operands;

    private List<String> operandTypes;

    public LogicParamBag() {
        operands = new ArrayList<>();
        operandTypes = new ArrayList<>();
    }

    public LogicParamBag(ParamTypeEnum paramType, String operator, List<Object> operands) {
        this.paramType = paramType;
        this.operator = operator;
        this.operands = operands;
    }

    public String getOperandType(int index) {
        return operandTypes.get(index);
    }

    public Object getOperand(int index) {
        return operands.get(index);
    }
}
