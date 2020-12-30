import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.logic.bool.BoolLogic;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BoolLogicTest {
    @SneakyThrows
    @Test
    public void testEqual() {
        BoolLogic boolLogic = new BoolLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.bool);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(Arrays.asList(true, true));
        Assert.assertTrue(boolLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(true, false));
        Assert.assertFalse(boolLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(false, true));
        Assert.assertFalse(boolLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(false, false));
        Assert.assertTrue(boolLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testNotEqual() {
        BoolLogic boolLogic = new BoolLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.bool);
        paramBag.setOperator(OperatorConst.NOT_EQUAL);
        paramBag.setOperands(Arrays.asList(true, true));
        Assert.assertFalse(boolLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(true, false));
        Assert.assertTrue(boolLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(false, true));
        Assert.assertTrue(boolLogic.process(paramBag));
        paramBag.setOperands(Arrays.asList(false, false));
        Assert.assertFalse(boolLogic.process(paramBag));
    }
}
