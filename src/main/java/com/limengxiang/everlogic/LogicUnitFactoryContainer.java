package com.limengxiang.everlogic;

import com.limengxiang.everlogic.logic.AbstractLogicUnit;
import com.limengxiang.everlogic.logic.DefaultLogicUnitFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class LogicUnitFactoryContainer {

    private List<ILogicUnitFactory> factories;

    private LogicEvaluator evaluator;

    public LogicUnitFactoryContainer() {
        factories = new ArrayList<>();
        factories.add(new DefaultLogicUnitFactory());
    }

    public void addFactory(ILogicUnitFactory factory) {
        factories.add(factory);
    }

    public void setEvaluator(LogicEvaluator evaluator) {
        this.evaluator = evaluator;
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
            throw new RuntimeException("Unsupported condition unit id:" + id);
        }

        if (logicUnit instanceof AbstractLogicUnit) {
            ((AbstractLogicUnit) logicUnit).setEvaluator(evaluator);
        }

        return logicUnit;
    }
}
