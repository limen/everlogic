package com.limengxiang.everlogic.condition;

import com.limengxiang.everlogic.LogicTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class AggregateGroup {

    private LogicTypeEnum logicType;
    private List<Group> groups;

    public AggregateGroup() {}

    public AggregateGroup(LogicTypeEnum logicType, List<Group> groups) {
        this.logicType = logicType;
        this.groups = groups;
    }

    public boolean process() throws Exception {
        Boolean result = null;
        for (Group group : groups) {
            boolean logicResult = group.process();
            if (logicType.equals(LogicTypeEnum.and)) {
                result = result == null ? logicResult : result && logicResult;
                if (!result) {
                    break;
                }
            } else if (logicType.equals(LogicTypeEnum.or)) {
                result = result == null ? logicResult : result || logicResult;
                if (result) {
                    break;
                }
            } else if (logicType.equals(LogicTypeEnum.xor)) {
                result = result == null ? logicResult : result != logicResult;
            }
        }
        return result != null && result;
    }

}
