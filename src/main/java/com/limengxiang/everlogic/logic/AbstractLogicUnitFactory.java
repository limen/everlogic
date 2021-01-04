package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicUnit;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public abstract class AbstractLogicUnitFactory {

    protected LogicUnitFactoryContainer container;

    public abstract LogicUnit getLogicUnit(Object id);

    public void setContainer(LogicUnitFactoryContainer container) {
        this.container = container;
    }

}
