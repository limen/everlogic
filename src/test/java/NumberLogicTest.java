import com.limengxiang.everlogic.LogicRule;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.OperandTypeEnum;
import com.limengxiang.everlogic.logic.NumberLogic;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NumberLogicTest {

    @Test
    public void testEqual() {
        LogicRule paramBag = new LogicRule();
        paramBag.setParamType(OperandTypeEnum.number);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(Arrays.asList(1000, 1000));
        NumberLogic numberLogic = new NumberLogic();
        Assert.assertTrue(numberLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList(1000,1001));
        Assert.assertFalse(numberLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList(1000,"1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList(1000.0,"1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList("1000.0","1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(Arrays.asList("1000.0","1000.01"));
        Assert.assertFalse(numberLogic.process(paramBag.getOperator(), paramBag.getOperands()));
    }
}
