package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.logic.bool.BoolLogic;
import com.limengxiang.everlogic.logic.number.NumberLogic;
import com.limengxiang.everlogic.logic.string.StringLogic;

public class LogicUnitFactory {

    private static final NumberLogic numberLogic;
    private static final StringLogic stringLogic;
    private static final BoolLogic boolLogic;

    static {
        numberLogic = new NumberLogic();
        stringLogic = new StringLogic();
        boolLogic = new BoolLogic();
    }

    public static LogicUnit getLogicUnit(LogicParamBag paramBag) throws Exception {
        switch (paramBag.getParamType()) {
            case number:
                return numberLogic;
            case string:
                return stringLogic;
            case bool:
                return boolLogic;
            default:
                throw new Exception("Unsupported param type:" + paramBag.getParamType());
        }
    }
}
