package com.limengxiang.everlogic.extend;

import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.logic.AbstractLogicUnitFactory;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class CustomLogicUnitFactory extends AbstractLogicUnitFactory {

    public LogicUnit getLogicUnit(Object id) {
        if (!(id instanceof CustomParamTypeEnum)) {
            return null;
        }
        switch ((CustomParamTypeEnum) id) {
            case string_ci:
                return new StringCILogic();
            default:
                return null;
        }
    }
}
