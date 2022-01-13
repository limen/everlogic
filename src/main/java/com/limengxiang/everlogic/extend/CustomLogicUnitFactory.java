package com.limengxiang.everlogic.extend;

import com.limengxiang.everlogic.ILogicUnit;
import com.limengxiang.everlogic.ILogicUnitFactory;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class CustomLogicUnitFactory implements ILogicUnitFactory {

    public ILogicUnit getLogicUnit(Object id) {
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
