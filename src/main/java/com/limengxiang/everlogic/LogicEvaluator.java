package com.limengxiang.everlogic;


/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class LogicEvaluator {

    protected LogicUnitFactoryContainer logicUnitFactoryContainer;
    protected IRegExCache regExCache;

    public LogicEvaluator() {
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
        logicUnitFactoryContainer.setEvaluator(this);
        regExCache = new DefaultRegExCache();
    }

    public void addLogicUnitFactory(ILogicUnitFactory factory) {
        logicUnitFactoryContainer.addFactory(factory);
    }

    public void setRegExCache(IRegExCache cache) {
        regExCache = cache;
    }

    public IRegExCache getRegExCache() {
        return regExCache;
    }

    public LogicUnitFactoryContainer getLogicUnitFactoryContainer() {
        return logicUnitFactoryContainer;
    }

    public boolean eval(LogicRule logicRule) {
        return doEval(logicRule);
    }

    private boolean doEval(LogicRule rule) {
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
                .getLogicUnit(rule.getParamType())
                .process(rule.getOperator(), rule.getOperands());
    }
}
