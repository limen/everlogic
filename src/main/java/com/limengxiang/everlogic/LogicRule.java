package com.limengxiang.everlogic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.limengxiang.everlogic.formatter.Formatter;

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
public final class LogicRule {

    @JsonIgnore
    private LogicEvaluator evaluator;

    private LogicConditionEnum condition;

    private Object paramType;

    private Formatter formatter;

    /**
     * @see OperatorConst
     */
    private String operator;

    private List<Object> operands;

    private Iterable<LogicRule> rules;

    public LogicRule() {
        condition = LogicConditionEnum.and;
    }

    public LogicRule(LogicConditionEnum cond) {
        this.condition = cond;
    }

    public LogicRule(Object paramType, String operator, List<Object> operands) {
        this.paramType = paramType;
        this.operator = operator;
        this.operands = operands;
    }

    public LogicEvaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(LogicEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public LogicConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(LogicConditionEnum condition) {
        this.condition = condition;
    }

    public Object getParamType() {
        return paramType;
    }

    public void setParamType(Object paramType) {
        this.paramType = paramType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<Object> getOperands() {
        return operands;
    }

    public void setOperands(List<Object> operands) {
        this.operands = operands;
    }

    public Iterable<LogicRule> getRules() {
        return rules;
    }

    public void setRules(Iterable<LogicRule> rules) {
        this.rules = rules;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }
}
