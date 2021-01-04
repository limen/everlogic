package extend;

import com.limengxiang.everlogic.LogicOperatorEnum;
import com.limengxiang.everlogic.LogicParamBag;
import com.limengxiang.everlogic.OperatorConst;
import com.limengxiang.everlogic.ParamTypeEnum;
import com.limengxiang.everlogic.extend.CustomLogicGroup;
import com.limengxiang.everlogic.extend.CustomParamTypeEnum;
import com.limengxiang.everlogic.group.LogicGroup;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author LI Mengxiang <limengxiang876@gmail.com>
 */
public class CustomLogicGroupTest {

    @SneakyThrows
    @Test
    public void test() {
        LogicParamBag p1 = new LogicParamBag();
        p1.setParamType(CustomParamTypeEnum.string_ci);
        p1.setOperator(OperatorConst.EQUAL);
        p1.setOperands(Arrays.asList("abc", "ABC"));
        LogicParamBag p2 = new LogicParamBag();
        p2.setParamType(CustomParamTypeEnum.string_ci);
        p2.setOperator(OperatorConst.NOT_EQUAL);
        p2.setOperands(Arrays.asList("abc", "abcd"));

        LogicGroup logicGroup = new CustomLogicGroup();
        logicGroup.setLogicOperator(LogicOperatorEnum.and);
        logicGroup.setParamBags(Arrays.asList(p1, p2));
        Assert.assertTrue(logicGroup.process());

        logicGroup.setLogicOperator(LogicOperatorEnum.or);
        Assert.assertTrue(logicGroup.process());

        logicGroup.setLogicOperator(LogicOperatorEnum.xor);
        Assert.assertFalse(logicGroup.process());
    }
}
