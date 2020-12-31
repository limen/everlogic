import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.logic.array.StringArrLogic;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class StringArrLogicTest {
    @SneakyThrows
    @Test
    public void testEqual() {
        StringArrLogic arrLogic = new StringArrLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.strArr);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "world", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("world", "hello", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "everlogic", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        Arrays.asList("everlogic", "everlogic", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic"),
                        Arrays.asList("everlogic", "everlogic", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        Arrays.asList("everlogic", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        "everlogic"
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        ""
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        null
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testNotEqual() {
        StringArrLogic arrLogic = new StringArrLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.strArr);
        paramBag.setOperator(OperatorConst.NOT_EQUAL);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "world", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("world", "hello", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "everlogic", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        Arrays.asList("everlogic", "everlogic", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testContain() {
        StringArrLogic arrLogic = new StringArrLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.strArr);
        paramBag.setOperator(OperatorConst.CONTAIN);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "world", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("world", "hello", "everlogic"),
                        Arrays.asList("hello", "world")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "everlogic", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        Arrays.asList("everlogic", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        "everlogic"
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        ""
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        null
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        Collections.emptyList()
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Collections.emptyList(),
                        Arrays.asList("everlogic", "everlogic", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Collections.emptyList(),
                        null
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
        paramBag.setOperands(
                Arrays.asList(
                        Collections.emptyList(),
                        ""
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag));
    }

    @SneakyThrows
    @Test
    public void testInside() {
        StringArrLogic arrLogic = new StringArrLogic();
        LogicParamBag paramBag = new LogicParamBag();
        paramBag.setParamType(ParamTypeEnum.strArr);
        paramBag.setOperator(OperatorConst.CONTAIN);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "world", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag));

    }
}
