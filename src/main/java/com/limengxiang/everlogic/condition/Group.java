package com.limengxiang.everlogic.condition;

import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicTypeEnum;
import com.limengxiang.everlogic.logic.LogicFacade;
import lombok.Data;

import java.util.List;

@Data
public class Group {

    private LogicTypeEnum logicType;
    private List<LogicParamBag> paramBags;

    public Group() {}

    public Group(LogicTypeEnum logicType, List<LogicParamBag> paramBags) {
        this.logicType = logicType;
        this.paramBags = paramBags;
    }

    public boolean process() throws Exception {
        Boolean result = null;
        for (LogicParamBag paramBag : paramBags) {
            boolean logicResult = LogicFacade.process(paramBag);
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
