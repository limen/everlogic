package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.LogicParamBag;

public class LogicFacade {

    public static boolean process(LogicParamBag paramBag) throws Exception {
        return LogicUnitFactory.getLogicUnit(paramBag).process(paramBag);
    }

}
