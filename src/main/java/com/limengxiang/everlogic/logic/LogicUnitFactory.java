package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.logic.array.NumberArrLogic;
import com.limengxiang.everlogic.logic.array.StringArrLogic;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class LogicUnitFactory {

    private LogicUnitFactoryContainer container;

    public void setContainer(LogicUnitFactoryContainer container) {
        this.container = container;
    }

    public LogicUnit getLogicUnit(Object paramType) {
        if (!(paramType instanceof ParamTypeEnum)) {
            return null;
        }
        switch ((ParamTypeEnum) paramType) {
            case number:
                return new NumberLogic();
            case string:
                return new StringLogic();
            case bool:
                return new BoolLogic();
            case json:
                JSONLogic jsonLogic = new JSONLogic();
                jsonLogic.setLogicUnitFactoryContainer(container);
                return jsonLogic;
            case date:
                return new DateLogic();
            case numArr:
                return new NumberArrLogic();
            case strArr:
                return new StringArrLogic();
            default:
                return null;
        }
    }
}
