import com.limengxiang.everlogic.LogicRule;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.OperandTypeEnum;
import com.limengxiang.everlogic.logic.array.StringArrLogic;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class StringArrLogicTest {
    @Test
    public void testEqual() {
        StringArrLogic arrLogic = new StringArrLogic();
        LogicRule paramBag = new LogicRule();
        paramBag.setParamType(OperandTypeEnum.str_arr);
        paramBag.setOperator(OperatorConst.EQUAL);
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "world", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("world", "hello", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("hello", "everlogic", "everlogic"),
                        Arrays.asList("hello", "world", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        Arrays.asList("everlogic", "everlogic", "everlogic")
                )
        );
        Assert.assertTrue(arrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic"),
                        Arrays.asList("everlogic", "everlogic", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
        paramBag.setOperands(
                Arrays.asList(
                        Arrays.asList("everlogic", "everlogic", "everlogic"),
                        Arrays.asList("everlogic", "everlogic")
                )
        );
        Assert.assertFalse(arrLogic.process(paramBag.getOperator(), paramBag.getOperands()));
    }
}
