import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.logic.array.NumberArrLogic;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class NumberArrLogicTest {

    @SneakyThrows
    @Test
    public void testEqual() {
        NumberArrLogic numberArrLogic = new NumberArrLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.numArr);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000, 1000, 10001),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001,1000,1000),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        Arrays.asList(1000,1000,10001,1000)
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        100.0
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        100000
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testNotEqual() {
        NumberArrLogic numberArrLogic = new NumberArrLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.numArr);
        paramBag.setOperator(OperatorConst.NOT_EQUAL);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000, 1000, 10001),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001,1000,1000),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        Arrays.asList(1000,1000,10001,1000)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        100.0
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(10001.0,1000.0,1000),
                        100000
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testContain() {
        NumberArrLogic numberArrLogic = new NumberArrLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.numArr);
        paramBag.setOperator(OperatorConst.CONTAIN);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000,1000,10001),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000,1000,10001),
                        Arrays.asList(1000,1000,10001,100002)
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000, 1000, 10001),
                        Arrays.asList(1000,1000)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000, 1000, 10001),
                        Arrays.asList(1000,1000,1000)
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000, 1000, 10001),
                        1000
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000, 1000, 10001),
                        null
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testInside() {
        NumberArrLogic numberArrLogic = new NumberArrLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.numArr);
        paramBag.setOperator(OperatorConst.INSIDE);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000,1000,10001),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000,1000,10001),
                        Arrays.asList(1000,1000,10001,10002)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList(1000,1000,10001,10002),
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        1000,
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertTrue(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        999,
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        null,
                        Arrays.asList(1000,1000,10001)
                )
        );
        Assert.assertFalse(numberArrLogic.process(paramBag));
    }
}
