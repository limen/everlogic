package com.limengxiang.everlogic.extend;

import com.limengxiang.everlogic.LogicEvaluator;
import com.limengxiang.everlogic.LogicUnitFactoryContainer;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class CustomLogicGroup extends LogicEvaluator {

    public CustomLogicGroup() {
        logicUnitFactoryContainer = new LogicUnitFactoryContainer();
        logicUnitFactoryContainer.addFactory(new CustomLogicUnitFactory());
    }

}
