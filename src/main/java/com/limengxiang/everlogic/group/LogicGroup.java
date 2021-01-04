package com.limengxiang.everlogic.group;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicOperatorEnum;
import com.limengxiang.everlogic.LogicResult;
import com.limengxiang.everlogic.logic.LogicUnitFactoryContainer;
import com.limengxiang.everlogic.util.LogicUtil;
import lombok.Data;

import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 * 逻辑表达式抽象，例如
 * a > b and b == c and c contain d
 * 不支持逻辑操作符混排，如
 * a > b and b == c or c contain d
 */
@Data
public class LogicGroup {

    protected LogicOperatorEnum logicOperator;
    protected List<LogicParamBag> paramBags;
    protected LogicUnitFactoryContainer logicUnitFactoryContainer;

    public LogicGroup() {
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
    }

    public LogicGroup(LogicOperatorEnum operator, List<LogicParamBag> paramBags) {
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
        this.logicOperator = operator;
        this.paramBags = paramBags;
    }

    public boolean process() throws Exception {
        LogicResult groupResult = new LogicResult(null, true);
        for (LogicParamBag paramBag : paramBags) {
            boolean logicUnitResult = logicUnitFactoryContainer.getLogicUnit(paramBag.getParamType()).process(paramBag);
            groupResult = LogicUtil.binaryLogic(groupResult.getResult(), logicUnitResult, logicOperator);
            if (!groupResult.getContinuable()) {
                break;
            }
        }
        return groupResult.getResult() != null && groupResult.getResult();
    }
}
