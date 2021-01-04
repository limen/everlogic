package com.limengxiang.everlogic.util;

import com.limengxiang.everlogic.LogicOperatorEnum;
import com.limengxiang.everlogic.LogicResult;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class LogicUtil {

    /**
     * @param prev
     * @param curr
     * @param logicOperator
     * @return
     */
    public static LogicResult binaryLogic(Boolean prev, Boolean curr, LogicOperatorEnum logicOperator) {
        LogicResult result = new LogicResult(null, true);
        if (logicOperator.equals(LogicOperatorEnum.and)) {
            prev = prev == null ? curr : prev && curr;
            if (!prev) {
                result.setContinuable(false);
            }
        } else if (logicOperator.equals(LogicOperatorEnum.or)) {
            prev = prev == null ? curr : prev || curr;
            if (prev) {
                result.setContinuable(false);
            }
        } else if (logicOperator.equals(LogicOperatorEnum.xor)) {
            prev = prev == null ? curr : prev != curr;
            result.setContinuable(true);
        }
        result.setResult(prev);
        return result;
    }
}
