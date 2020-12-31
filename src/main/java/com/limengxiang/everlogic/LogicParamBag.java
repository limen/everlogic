package com.limengxiang.everlogic;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑运算抽象
 * 支持如下逻辑运算：
 * - 数字：a > b, a < b, a == b, a != b
 * - 字符串：a == b, a != b, a start_with b, a end_with b, a >/>=/</<= b, a contain b, a inside b
 * - 布尔： a == b, a != b
 * - 数组： a == b, a != b, a contain b, a inside b
 */
@Data
public final class LogicParamBag {

    private ParamTypeEnum paramType;

    /**
     * @see OperatorConst
     */
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
