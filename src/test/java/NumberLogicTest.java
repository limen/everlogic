import com.limengxiang.everlogic.FormatterConst;
import com.limengxiang.everlogic.LogicRule;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.OperandTypeEnum;
import com.limengxiang.everlogic.formatter.NumberFormatter;
import com.limengxiang.everlogic.logic.NumberLogic;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

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

    @Test
    public void testFormatter() {
        NumberLogic numberLogic = new NumberLogic();
        NumberFormatter formatter = new NumberFormatter();
        formatter.setType(FormatterConst.Numbers.TYPE_ROUND);
        numberLogic.setFormatter(formatter);
        Assert.assertTrue(numberLogic.process(OperatorConst.EQUAL, Arrays.asList(1.11111, 1.11222)));
        formatter.setType(FormatterConst.Numbers.TYPE_CEIL);
        Assert.assertTrue(numberLogic.process(OperatorConst.EQUAL, Arrays.asList(1.11111, 1.9222)));
        formatter.setType(FormatterConst.Numbers.TYPE_CUT);
        formatter.setArgs(Collections.singletonList(2));
        Assert.assertTrue(numberLogic.process(OperatorConst.EQUAL, Arrays.asList(1.11111, 1.11222)));
    }
}
