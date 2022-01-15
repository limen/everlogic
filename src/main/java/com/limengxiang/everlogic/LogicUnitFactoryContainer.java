package com.limengxiang.everlogic;

import com.limengxiang.everlogic.logic.AbstractLogicUnit;
import com.limengxiang.everlogic.logic.DefaultLogicUnitFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class LogicUnitFactoryContainer {

    private final List<ILogicUnitFactory> factories;

    public LogicUnitFactoryContainer() {
        factories = new ArrayList<>();
        factories.add(new DefaultLogicUnitFactory());
    }

    public void addFactory(ILogicUnitFactory factory) {
        factories.add(factory);
    }

    public ILogicUnit getLogicUnit(Object id) throws RuntimeException {
        ILogicUnit logicUnit = null;
        for (ILogicUnitFactory factory : factories) {
            logicUnit = factory.getLogicUnit(id);
            if (logicUnit != null) {
                break;
            }
        }
        if (logicUnit == null) {
            throw new RuntimeException("Unsupported logic unit:" + id);
        }

        return logicUnit;
    }

    public ILogicUnit getLogicUnit(LogicRule logicRule) {
        ILogicUnit logicUnit = getLogicUnit(logicRule.getParamType());
        if (logicUnit instanceof AbstractLogicUnit) {
            ((AbstractLogicUnit) logicUnit).setFormatter(logicRule.getFormatter());
            ((AbstractLogicUnit) logicUnit).setEvaluator(logicRule.getEvaluator());
        }
        return logicUnit;
    }
}
