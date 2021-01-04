import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.logic.StringLogic;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class StringLogicTest {

    @SneakyThrows
    @Test
    public void testEqual() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "hello1"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("Hello1", "hello1"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", null));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, null));
        Assert.assertTrue(stringLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testNotEqual() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.NOT_EQUAL);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "hello1"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("Hello1", "hello1"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, null));
        Assert.assertFalse(stringLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testGreaterThan() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.GREATER_THAN);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello1", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("Hello1", "hello1"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, ""));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", null));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, null));
        Assert.assertFalse(stringLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testGTE() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.GREATER_THAN_OR_EQUAL);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello1", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("Hello1", "hello1"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, ""));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", null));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, null));
        Assert.assertTrue(stringLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testLessThan() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.LESS_THAN);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello1", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("Hello1", "hello1"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", null));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, null));
        Assert.assertFalse(stringLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testLTE() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.LESS_THAN_OR_EQUAL);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello1", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("Hello1", "hello1"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", null));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, null));
        Assert.assertTrue(stringLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testStartWith() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.START_WITH);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "ello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "h"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "hello1"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello1", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO1", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO1", "HELLO"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, "HELLO"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO", null));
        Assert.assertFalse(stringLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testEndWith() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.END_WITH);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "ello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "h"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "hello1"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello1", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO1", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO1", "ELLO1"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, "HELLO"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO", null));
        Assert.assertFalse(stringLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testContain() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.CONTAIN);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "ello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "h"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "hello1"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello1", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO1", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO1", "ELLO1"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, "HELLO"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO", null));
        Assert.assertFalse(stringLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testInside() {
        StringLogic stringLogic = new StringLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.string);
        paramBag.setOperator(OperatorConst.INSIDE);
        paramBag.setOperands(Arrays.asList("hello", "hello"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "ello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "h"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", ""));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello", "hello1"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("hello1", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO1", "hello"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("ELLO1", "HELLO1"));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("", ""));
        Assert.assertTrue(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(null, "HELLO"));
        Assert.assertFalse(stringLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList("HELLO", null));
        Assert.assertFalse(stringLogic.process(paramBag));
    }

}
