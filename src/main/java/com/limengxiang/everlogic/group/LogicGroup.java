package com.limengxiang.everlogic.group;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicOperatorEnum;
import com.limengxiang.everlogic.logic.LogicFacade;
import lombok.Data;

import java.util.List;

/**
 * 逻辑表达式抽象，例如
 * a > b and b == c and c contain d
 * 不支持逻辑操作符混排，如
 * a > b and b == c or c contain d
 */
@Data
public class LogicGroup {

    private LogicOperatorEnum logicOperator;
    private List<LogicParamBag> paramBags;

    public LogicGroup() {}

    public LogicGroup(LogicOperatorEnum operator, List<LogicParamBag> paramBags) {
        this.logicOperator = operator;
        this.paramBags = paramBags;
    }

    public boolean process() throws Exception {
        Boolean result = null;
        for (LogicParamBag paramBag : paramBags) {
            boolean logicResult = LogicFacade.process(paramBag);
            if (logicOperator.equals(LogicOperatorEnum.and)) {
                result = result == null ? logicResult : result && logicResult;
                if (!result) {
                    break;
                }
            } else if (logicOperator.equals(LogicOperatorEnum.or)) {
                result = result == null ? logicResult : result || logicResult;
                if (result) {
                    break;
                }
            } else if (logicOperator.equals(LogicOperatorEnum.xor)) {
                result = result == null ? logicResult : result != logicResult;
            }
        }
        return result != null && result;
    }
}
