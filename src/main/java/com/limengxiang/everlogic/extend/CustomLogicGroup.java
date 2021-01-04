package com.limengxiang.everlogic.extend;

import com.limengxiang.everlogic.LogicOperatorEnum;
import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.group.LogicGroup;
import com.limengxiang.everlogic.logic.LogicUnitFactoryContainer;

import java.util.List;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class CustomLogicGroup extends LogicGroup {

    public CustomLogicGroup() {
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
        logicUnitFactoryContainer.addFactory(new CustomLogicUnitFactory());
    }

    public CustomLogicGroup(LogicOperatorEnum operator, List<LogicParamBag> paramBags) {
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
        this.logicOperator = operator;
        this.paramBags = paramBags;
    }

}
