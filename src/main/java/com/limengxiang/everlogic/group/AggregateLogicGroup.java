package com.limengxiang.everlogic.group;

import com.limengxiang.everlogic.LogicOperatorEnum;
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

    private LogicOperatorEnum logicOperator;
    private List<LogicGroup> groups;

    public AggregateLogicGroup() {}

    public AggregateLogicGroup(LogicOperatorEnum logicOperator, List<LogicGroup> groups) {
        this.logicOperator = logicOperator;
        this.groups = groups;
    }

    public boolean process() throws Exception {
        Boolean result = null;
        for (LogicGroup group : groups) {
            boolean logicResult = group.process();
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
