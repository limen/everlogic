package com.limengxiang.everlogic;


/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class LogicEvaluator {

    protected LogicUnitFactoryContainer logicUnitFactoryContainer;
    protected IReContainer reContainer;

    public LogicEvaluator() {
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
        reContainer = new DefaultReContainer();
    }

    public void addLogicUnitFactory(ILogicUnitFactory factory) {
        logicUnitFactoryContainer.addFactory(factory);
    }

    public void setReContainer(IReContainer cache) {
        reContainer = cache;
    }

    public IReContainer getReContainer() {
        return reContainer;
    }

    public void setLogicUnitFactoryContainer(LogicUnitFactoryContainer container) {
        logicUnitFactoryContainer = container;
    }

    public LogicUnitFactoryContainer getLogicUnitFactoryContainer() {
        return logicUnitFactoryContainer;
    }

    public boolean eval(LogicRule logicRule) {
        return doEval(logicRule);
    }

    private boolean doEval(LogicRule rule) {
        rule.setEvaluator(this);
        if (rule.getCondition() == null || rule.getRules() == null) {
            return evalSimpleRule(rule);
        }
        boolean isAnd = rule.getCondition().equals(LogicConditionEnum.and);
        for (LogicRule r : rule.getRules()) {
            boolean b = doEval(r);
            if (isAnd && !b) {
                return false;
            }
            if (!isAnd && b) {
                return true;
            }
        }
        return isAnd;
    }

    private boolean evalSimpleRule(LogicRule rule) {
        return logicUnitFactoryContainer
                .getLogicUnit(rule)
                .process(rule.getOperator(), rule.getOperands());
    }
}
