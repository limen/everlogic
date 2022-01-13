package com.limengxiang.everlogic.logic;

import com.limengxiang.everlogic.ILogicUnit;
import com.limengxiang.everlogic.ILogicUnitFactory;
import com.limengxiang.everlogic.OperandTypeEnum;
import com.limengxiang.everlogic.logic.array.NumberArrLogic;
import com.limengxiang.everlogic.logic.array.StringArrLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 */
public class DefaultLogicUnitFactory implements ILogicUnitFactory {

    private ReentrantLock lock = new ReentrantLock();

    private Map<OperandTypeEnum, AbstractLogicUnit> logicUnitMap;

    private void init() {
        if (logicUnitMap == null) {
            logicUnitMap = new HashMap<>();
            logicUnitMap.put(OperandTypeEnum.number, new NumberLogic());
            logicUnitMap.put(OperandTypeEnum.string, new StringLogic());
            logicUnitMap.put(OperandTypeEnum.bool, new BoolLogic());
            logicUnitMap.put(OperandTypeEnum.json, new JSONLogic());
            logicUnitMap.put(OperandTypeEnum.date, new DateLogic());
            logicUnitMap.put(OperandTypeEnum.num_arr, new NumberArrLogic());
            logicUnitMap.put(OperandTypeEnum.str_arr, new StringArrLogic());
        }
    }

    public ILogicUnit getLogicUnit(Object paramType) {
        if (!(paramType instanceof OperandTypeEnum)) {
            return null;
        }
        if (logicUnitMap == null) {
            lock.lock();
            init();
            lock.unlock();
        }
        return logicUnitMap.get(paramType);
    }
}
