package com.limengxiang.everlogic.logic.array;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicUnit;

public class NumberArrLogic implements LogicUnit {

    private enum NumberArrOperator {
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
