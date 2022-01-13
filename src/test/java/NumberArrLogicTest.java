import com.limengxiang.everlogic.LogicRule;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.OperandTypeEnum;
import com.limengxiang.everlogic.logic.array.NumberArrLogic;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class NumberArrLogicTest {

    @Test
    public void testEqual() {
        NumberArrLogic numberArrLogic = new NumberArrLogic();
        LogicRule paramBag = new LogicRule();
        paramBag.setParamType(OperandTypeEnum.num_arr);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000, 1000, 10001),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001,1000,1000),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        Arrays.asList(1000,1000,10001,1000)
                )
        );
    }
}
