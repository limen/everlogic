import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.logic.number.NumberLogic;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NumberLogicTest {

    @SneakyThrows
    @Test
    public void testEqual() {
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.number);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(Arrays.asList(1000, 1000));
        NumberLogic numberLogic = new NumberLogic();
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1000,1001));
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1000,"1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1000.0,"1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.0","1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.0","1000.01"));
        Assert.assertFalse(numberLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testNotEqual() {
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.number);
        paramBag.setOperator(OperatorConst.NOT_EQUAL);
        paramBag.setOperands(Arrays.asList(1000, 1000));
        NumberLogic numberLogic = new NumberLogic();
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.0","1000.01"));
        Assert.assertTrue(numberLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testGreaterThan() {
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.number);
        paramBag.setOperator(OperatorConst.GREATER_THAN);
        paramBag.setOperands(Arrays.asList(1000, 1000));
        NumberLogic numberLogic = new NumberLogic();
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1001,1000));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1000,"1000.0"));
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1000.0,"999.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.1","1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.0","1000.01"));
        Assert.assertFalse(numberLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testGTE() {
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.number);
        paramBag.setOperator(OperatorConst.GREATER_THAN_OR_EQUAL);
        paramBag.setOperands(Arrays.asList(1000, 1000));
        NumberLogic numberLogic = new NumberLogic();
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1001,1000));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1000,"1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(999.0,"1000.0"));
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.1","1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.0","1000.01"));
        Assert.assertFalse(numberLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testLessThan() {
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.number);
        paramBag.setOperator(OperatorConst.LESS_THAN);
        paramBag.setOperands(Arrays.asList(1000, 1000));
        NumberLogic numberLogic = new NumberLogic();
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1001,1000));
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(999,"1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(999.0,"1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.1","1000.0"));
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.0","1000.01"));
        Assert.assertTrue(numberLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testLTE() {
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.number);
        paramBag.setOperator(OperatorConst.LESS_THAN_OR_EQUAL);
        paramBag.setOperands(Arrays.asList(1000, 1000));
        NumberLogic numberLogic = new NumberLogic();
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(1001,1000));
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(999,"1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(999.0,"1000.0"));
        Assert.assertTrue(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.1","1000.0"));
        Assert.assertFalse(numberLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("1000.0","1000.01"));
        Assert.assertTrue(numberLogic.process(paramBag));
    }
}
