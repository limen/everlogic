package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicUnit;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
@Data
public class LogicUnitFactoryContainer {

    private List<LogicUnitFactory> factories;

    public LogicUnitFactoryContainer() {
        factories = new ArrayList<>();
        factories.add(new LogicUnitFactory());
    }

    public void addFactory(LogicUnitFactory factory) {
        factory.setContainer(this);
        factories.add(factory);
    }

    public LogicUnit getLogicUnit(Object id) throws Exception {
        LogicUnit logicUnit = null;
        for (LogicUnitFactory factory : factories) {
            logicUnit = factory.getLogicUnit(id);
            if (logicUnit != null) {
                break;
            }
        }
        if (logicUnit == null) {
            throw new Exception("Unsupported logic unit id:" + id);
        }
        return logicUnit;
    }
}
