package com.limengxiang.everlogic.logic.array;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;

public class BoolArrLogic implements LogicUnit {

    private enum BoolArrOperator {
        equal,
        ne,
        contain,
        inside,
    }

    @Override
    public boolean process(LogicParamBag paramBag) throws Exception {
        return false;
    }
}
