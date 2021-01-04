package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;
import com.limengxiang.everlogic.logic.array.NumberArrLogic;
import com.limengxiang.everlogic.logic.array.StringArrLogic;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class LogicUnitFactory {

    private static final NumberLogic numberLogic;
    private static final StringLogic stringLogic;
    private static final BoolLogic boolLogic;
    private static final JSONLogic jsonLogic;
    private static final DateLogic dateLogic;
    private static final StringArrLogic strArrLogic;
    private static final NumberArrLogic numArrLogic;

    static {
        numberLogic = new NumberLogic();
        stringLogic = new StringLogic();
        boolLogic = new BoolLogic();
        jsonLogic = new JSONLogic();
        dateLogic = new DateLogic();
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
            case date:
                return dateLogic;
            case numArr:
                return numArrLogic;
            case strArr:
                return strArrLogic;
            default:
                throw new Exception("Unsupported param type:" + paramBag.getParamType());
        }
    }
}
