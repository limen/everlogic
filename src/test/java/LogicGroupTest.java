import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.LogicOperatorEnum;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.group.LogicGroup;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LogicGroupTest {

    @SneakyThrows
    @Test
    public void testNumberGroup() {
        LogicParamBag p1 = new LogicParamBag();
        p1.setParamType(ParamTypeEnum.number);
        p1.setOperator(OperatorConst.EQUAL);
        p1.setOperands(Arrays.asList(1000, 1000.0));
        LogicParamBag p2 = new LogicParamBag();
        p2.setParamType(ParamTypeEnum.number);
        p2.setOperator(OperatorConst.GREATER_THAN);
        p2.setOperands(Arrays.asList(1000, 1000.0));
        LogicParamBag p3 = new LogicParamBag();
        p3.setParamType(ParamTypeEnum.number);
        p3.setOperator(OperatorConst.GREATER_THAN_OR_EQUAL);
        p3.setOperands(Arrays.asList("1000", "999.9"));

        LogicGroup logicGroup = new LogicGroup();
        logicGroup.setLogicOperator(LogicOperatorEnum.and);
        logicGroup.setParamBags(Arrays.asList(p1, p2));
        Assert.assertFalse(logicGroup.process());

        logicGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertTrue(logicGroup.process());

        logicGroup.setLogicOperator(LogicOperatorEnum.xor);
        Assert.assertTrue(logicGroup.process());

        logicGroup.setLogicOperator(LogicOperatorEnum.and);
        logicGroup.setParamBags(Arrays.asList(p1, p2, p3));
        Assert.assertFalse(logicGroup.process());
        logicGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertTrue(logicGroup.process());
        logicGroup.setLogicOperator(LogicOperatorEnum.xor);
        Assert.assertFalse(logicGroup.process());
    }

    @SneakyThrows
    @Test
    public void testStringGroup() {
        LogicParamBag p1 = new LogicParamBag();
        p1.setParamType(ParamTypeEnum.string);
        p1.setOperator(OperatorConst.EQUAL);
        p1.setOperands(Arrays.asList("abc", "abc"));
        LogicParamBag p2 = new LogicParamBag();
        p2.setParamType(ParamTypeEnum.string);
        p2.setOperator(OperatorConst.GREATER_THAN);
        p2.setOperands(Arrays.asList("abc", "abcd"));
        LogicParamBag p3 = new LogicParamBag();
        p3.setParamType(ParamTypeEnum.string);
        p3.setOperator(OperatorConst.START_WITH);
        p3.setOperands(Arrays.asList("abcd", "abc"));

        LogicGroup logicGroup = new LogicGroup();
        logicGroup.setLogicOperator(LogicOperatorEnum.and);
        logicGroup.setParamBags(Arrays.asList(p1, p2));
        Assert.assertFalse(logicGroup.process());

        logicGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertTrue(logicGroup.process());

        logicGroup.setLogicOperator(LogicOperatorEnum.xor);
        Assert.assertTrue(logicGroup.process());

        logicGroup.setLogicOperator(LogicOperatorEnum.and);
        logicGroup.setParamBags(Arrays.asList(p1, p2, p3));
        Assert.assertFalse(logicGroup.process());
        logicGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertTrue(logicGroup.process());
        logicGroup.setLogicOperator(LogicOperatorEnum.xor);
        Assert.assertFalse(logicGroup.process());
    }
}
