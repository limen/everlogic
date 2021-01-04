package com.limengxiang.everlogic;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 * 逻辑运算抽象
 * 支持如下逻辑运算：
 * - 数字：a > b, a < b, a == b, a != b
 * - 字符串：a == b, a != b, a start_with b, a end_with b, a >/>=/</<= b, a contain b, a inside b
 * - 布尔： a == b, a != b
 * - 数组： a == b, a != b, a contain b, a inside b
 */
@Data
public final class LogicParamBag {

    private Object paramType;

    /**
     * @see OperatorConst
     */
    private String operator;

    private List<Object> operands;

    public LogicParamBag() {
        operands = new ArrayList<>();
    }

    public LogicParamBag(ParamTypeEnum paramType, String operator, List<Object> operands) {
        this.paramType = paramType;
        this.operator = operator;
        this.operands = operands;
    }

    public Object getOperand(int index) {
        return operands.get(index);
    }
}
