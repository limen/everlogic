package com.limengxiang.everlogic;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class LogicEvaluator {

    protected Class<? extends IEnvContainer> envContainerClass;
    protected LogicUnitFactoryContainer logicUnitFactoryContainer;
    protected IReContainer reContainer;

    public LogicEvaluator() {
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
        envContainerClass = SimpleEnvContainer.class;
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
        return doEval(logicRule, null);
    }

    public boolean eval(LogicRule logicRule, Map<String, Object> env) {
        return doEval(logicRule, env);
    }

    private boolean doEval(LogicRule rule, Map<String, Object> env) {
        if (rule.getCondition() == null || rule.getRules() == null) {
            return evalSimpleRule(rule, env);
        }
        boolean isAnd = rule.getCondition().equals(LogicConditionEnum.and);
        for (LogicRule r : rule.getRules()) {
            boolean b = doEval(r, env);
            if (isAnd && !b) {
                return false;
            }
            if (!isAnd && b) {
                return true;
            }
        }
        return isAnd;
    }

    private boolean evalSimpleRule(LogicRule rule, Map<String, Object> env) {
        List<Object> operands = new ArrayList<>();
        IEnvContainer envContainer;
        try {
            envContainer = envContainerClass.newInstance();
            envContainer.setEnv(env);
        } catch (Exception e) {
            throw new RuntimeException("env container init error:" + e.getMessage());
        }
        for (Object op : rule.getOperands()) {
            operands.add(envContainer.getValue(op));
        }
        return logicUnitFactoryContainer
                .getLogicUnit(rule)
                .process(rule.getOperator(), operands);
    }
}
