package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.logic.array.NumberArrLogic;
import com.limengxiang.everlogic.logic.array.StringArrLogic;
import com.limengxiang.everlogic.logic.bool.BoolLogic;
import com.limengxiang.everlogic.logic.json.JSONLogic;
import com.limengxiang.everlogic.logic.number.NumberLogic;
import com.limengxiang.everlogic.logic.string.StringLogic;

public class LogicUnitFactory {

    private static final NumberLogic numberLogic;
    private static final StringLogic stringLogic;
    private static final BoolLogic boolLogic;
    private static final JSONLogic jsonLogic;
    private static final StringArrLogic strArrLogic;
    private static final NumberArrLogic numArrLogic;

    static {
        numberLogic = new NumberLogic();
        stringLogic = new StringLogic();
        boolLogic = new BoolLogic();
        jsonLogic = new JSONLogic();
        strArrLogic = new StringArrLogic();
        numArrLogic = new NumberArrLogic();
    }

    public static LogicUnit getLogicUnit(LogicParamBag paramBag) throws Exception {
        switch (paramBag.getParamType()) {
            case number:
                return numberLogic;
            case string:
                return stringLogic;
            case bool:
                return boolLogic;
            case json:
                return jsonLogic;
            case numArr:
                return numArrLogic;
            case strArr:
                return strArrLogic;
            default:
                throw new Exception("Unsupported param type:" + paramBag.getParamType());
        }
    }
}
