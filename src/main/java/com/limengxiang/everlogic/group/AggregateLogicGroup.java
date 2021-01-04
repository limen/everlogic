package com.limengxiang.everlogic.group;

import com.limengxiang.everlogic.LogicOperatorEnum;
import com.limengxiang.everlogic.LogicResult;
import com.limengxiang.everlogic.logic.LogicUnitFactoryContainer;
import com.limengxiang.everlogic.util.LogicUtil;
import lombok.Data;

import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 *
 * 聚合逻辑表达式，由多个
 * @see LogicGroup
 * 例如
 * （a > b and b == c and c contain d） and (a1 > b1 or b1 == c1 or c contain d) and (a > b xor b == c xor c contain d)
 */
@Data
public class AggregateLogicGroup {

    protected LogicOperatorEnum logicOperator;
    protected List<LogicGroup> groups;
    protected LogicUnitFactoryContainer logicUnitFactoryContainer;

    public AggregateLogicGroup() {
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
    }

    public AggregateLogicGroup(LogicOperatorEnum logicOperator, List<LogicGroup> groups) {
        this.logicOperator = logicOperator;
        this.groups = groups;
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
    }

    public AggregateLogicGroup(LogicOperatorEnum logicOperator, List<LogicGroup> groups, LogicUnitFactoryContainer logicUnitFactory) {
        this.logicOperator = logicOperator;
        this.groups = groups;
        this.logicUnitFactoryContainer = logicUnitFactory;
    }

    public boolean process() throws Exception {
        LogicResult result = new LogicResult(null, true);
        for (LogicGroup group : groups) {
            if (logicUnitFactoryContainer != null && group.getLogicUnitFactoryContainer() == null) {
                group.setLogicUnitFactoryContainer(logicUnitFactoryContainer);
            }
            boolean logicResult = group.process();
            result = LogicUtil.binaryLogic(result.getResult(), logicResult, logicOperator);
        }
        return result.getResult() != null && result.getResult();
    }

}
